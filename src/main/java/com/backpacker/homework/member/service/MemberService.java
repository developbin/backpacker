package com.backpacker.homework.member.service;

import com.backpacker.homework.component.JwtAuthenticationProvider;
import com.backpacker.homework.member.domian.entity.MemberEntity;
import com.backpacker.homework.member.domian.repository.MemberRepository;
import com.backpacker.homework.member.dto.MemberDto;
import com.backpacker.homework.member.dto.MemberRequestDto;
import com.backpacker.homework.member.dto.MemberLoginRequestDto;
import com.backpacker.homework.member.mapper.MemberDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final RedisTemplate<String, String> redisTemplate;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(RedisTemplate<String, String> redisTemplate,
                         JwtAuthenticationProvider jwtAuthenticationProvider,
                         MemberRepository memberRepository,
                         PasswordEncoder passwordEncoder) {
        this.redisTemplate = redisTemplate;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public MemberDto joinMember(MemberRequestDto requestDto){
        MemberEntity memberEntity = MemberEntity.createBy(
                requestDto.getName(),
                requestDto.getNickName(),
                passwordEncoder.encode(requestDto.getPassword()),
                requestDto.getTelNumber(),
                requestDto.getEmail(),
                requestDto.getGender()
        );

        MemberEntity newMemberEntity = memberRepository.save(memberEntity);
        return MemberDtoConverter.convert(newMemberEntity);
    }

    public Map<String, String> loginMember(MemberLoginRequestDto requestDto){
        MemberEntity memberEntity = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), memberEntity.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        List<String> authorities = memberEntity.getAuthorities().stream().map(f->f.getAuthority()).collect(Collectors.toList());
        String token = jwtAuthenticationProvider.createToken(memberEntity.getEmail(), authorities);

        Map<String, String> data = new HashMap<>();
        data.put("accessToken",token);
        return data;
    }

    public void logout(String headerAuth){
        String accessToken = null;
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")){
            accessToken = headerAuth.substring(7, headerAuth.length());
        }
        Optional.ofNullable(accessToken).ifPresent(token -> {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(token, "logout");
        });
    }

    public MemberDto getMemberInfo(){
        MemberDto member = null;
        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(details != null && !(details instanceof String)){
            MemberEntity memberEntity = (MemberEntity) details;
            member = MemberDtoConverter.convert(memberEntity);
        }

        return member;
    }

}
