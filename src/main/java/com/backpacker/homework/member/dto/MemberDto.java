package com.backpacker.homework.member.dto;

import com.backpacker.homework.code.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MemberDto {

    private String email;

    private String name;

    private String nickName;

    private String telNumber;

    private Gender gender;

    @Builder
    public MemberDto(String email,
                     String name,
                     String nickName,
                     String telNumber,
                     Gender gender) {
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.telNumber = telNumber;
        this.gender = gender;
    }

}
