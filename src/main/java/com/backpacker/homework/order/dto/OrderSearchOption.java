package com.backpacker.homework.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(description = "주문검색조건")
public class OrderSearchOption {

    @ApiModelProperty(value = "이름")
    private String name;

    @ApiModelProperty(value = "이메일")
    private String email;

}
