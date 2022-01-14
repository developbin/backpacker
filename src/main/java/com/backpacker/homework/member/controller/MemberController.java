package com.backpacker.homework.member.controller;

import com.backpacker.homework.code.ApiResponseCode;
import com.backpacker.homework.component.JwtAuthenticationProvider;
import com.backpacker.homework.generator.ApiResponseGenerator;
import com.backpacker.homework.member.domian.repository.MemberRepository;
import com.backpacker.homework.member.dto.MemberDto;
import com.backpacker.homework.member.dto.MemberRequestDto;
import com.backpacker.homework.member.dto.MemberLoginRequestDto;
import com.backpacker.homework.member.service.MemberService;
import com.backpacker.homework.common.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
@Api(tags = {"회원 API"})
@RequestMapping("/v1/members")
public class MemberController {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @Autowired
    public MemberController(JwtAuthenticationProvider jwtAuthenticationProvider,
                            MemberRepository memberRepository,
                            PasswordEncoder passwordEncoder,
                            MemberService memberService) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberService = memberService;
    }

    @ApiOperation(tags = "회원 가입 API", value = "회원 가입", notes = "회원을 가입합니다.")
    @PostMapping("/join")
    public ResponseEntity<ApiResponse<MemberDto>> joinMember(@ApiParam(value = "회원 가입 요청", required = true) @RequestBody @Valid MemberRequestDto request){
        MemberDto member = memberService.joinMember(request);
        ApiResponse<MemberDto> apiResponse = ApiResponseGenerator.success(member);
        return ResponseEntity.ok(apiResponse);
    }

    @ApiOperation(tags = "회원 로그인 API", value = "회원 로그인", notes = "회원 로그인 이후 액세스 토큰을 발급합니다.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> loginMember(@ApiParam(value = "회원 로그인", required = true) @RequestBody @Valid MemberLoginRequestDto request){
        Map<String, String> data = memberService.loginMember(request);
        ApiResponse<Map<String, String>> apiResponse = ApiResponseGenerator.success(data);
        return ResponseEntity.ok(apiResponse);
    }

    @ApiOperation(tags = "회원 로그아웃 API", value = "회원 로그아웃", notes = "회원 로그아웃을 합니다. Authorization Bearer token은 필수입니다.")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request){
        memberService.logout(request.getHeader("Authorization"));
        ApiResponse<Void> apiResponse = ApiResponseGenerator.success();
        return ResponseEntity.ok(apiResponse);
    }

    @ApiOperation(tags = "회원 정보 API", value = "회원 정보", notes = "회원 정보를 노출합니다. Authorization Bearer token은 필수입니다.")
    @GetMapping(value = "/info")
    public ResponseEntity<?> getMemberInfo(){
        MemberDto memberDto = memberService.getMemberInfo();
        if(Objects.isNull(memberDto)){
            return new ResponseEntity<>(ApiResponseGenerator.fail(ApiResponseCode.RESOURCE_NOT_FOUND), HttpStatus.NOT_FOUND);
        }

        ApiResponse<MemberDto> apiResponse = ApiResponseGenerator.success(memberDto);
        return ResponseEntity.ok(apiResponse);
    }

}
