package com.backpacker.homework.member.mapper;

import com.backpacker.homework.member.domian.entity.MemberEntity;
import com.backpacker.homework.member.dto.MemberDto;
import com.backpacker.homework.member.dto.MemberRequestDto;

import java.util.Objects;

public class MemberDtoConverter {

    public static MemberDto convert(MemberEntity memberEntity) {
        if (Objects.isNull(memberEntity)) {
            return new MemberDto();
        }

        return MemberDto.builder()
                .name(memberEntity.getName())
                .nickName(memberEntity.getNickname())
                .telNumber(memberEntity.getTelNumber())
                .email(memberEntity.getEmail())
                .gender(memberEntity.getGender())
                .build();
    }

}
