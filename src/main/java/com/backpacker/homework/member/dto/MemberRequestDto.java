package com.backpacker.homework.member.dto;

import com.backpacker.homework.code.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
@ApiModel(description = "회원 가입 요청")
public class MemberRequestDto {

    @ApiModelProperty(value = "이메일", required = true)
    @NotEmpty(message = "이메일을 입력해 주시기 바랍니다.")
    @Size(max = 100, message = "최대 100자리 이하 입력해주세요.")
    @Pattern(regexp = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", message = "이메일 형식으로만 입력할 수 있습니다.")
    private String email;

    @ApiModelProperty(value = "패스워드", required = true)
    @NotEmpty(message = "패스워드를 입력해 주시기 바랍니다.")
    @Size(min = 10, message = "최소 10자리 입력해주세요.")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{10,}$", message = "영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함되어야 합니다.")
    private String password;

    @ApiModelProperty(value = "이름", required = true)
    @NotEmpty(message = "이름를 입력해 주시기 바랍니다.")
    @Size(max = 20, message = "최대 20자리 이하 입력해주세요.")
    @Pattern(regexp = "^[A-Zㄱ-힣 ]*$", message = "한글,영문 대소문자만 입력할 수 있습니다.")
    private String name;

    @ApiModelProperty(value = "별명", required = true)
    @NotEmpty(message = "별명을 입력해 주시기 바랍니다.")
    @Size(max = 30, message = "최대 30자리 이하 입력해주세요.")
    @Pattern(regexp = "^[a-z ]*$", message = "영문 소문자만 입력할 수 있습니다.")
    private String nickName;

    @ApiModelProperty(value = "전화번호", required = true)
    @NotEmpty(message = "전화번호를 입력해 주시기 바랍니다.")
    @Size(max = 20, message = "최대 20자리 이하 입력해주세요.")
    @Pattern(regexp = "^[0-9]*$", message = "숫자만 입력할 수 있습니다.")
    private String telNumber;

    @ApiModelProperty(value = "성별", required = true)
    @NotNull(message = "성별을 입력해 주시기 바랍니다.")
    private Gender gender;

    @Builder
    public MemberRequestDto(String email,
                            String password,
                            String name,
                            String nickName,
                            String telNumber,
                            Gender gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.telNumber = telNumber;
        this.gender = gender;
    }

}
