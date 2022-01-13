package com.backpacker.homework.member.dto;

import com.backpacker.homework.code.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
@ApiModel(description = "회원 로그인 요청")
public class MemberLoginRequestDto {

    @ApiModelProperty(value = "이메일", required = true)
    @NotEmpty(message = "이메일을 입력해 주시기 바랍니다.")
    private String email;

    @ApiModelProperty(value = "패스워드", required = true)
    @NotEmpty(message = "패스워드를 입력해 주시기 바랍니다.")
    private String password;

}
