package com.backpacker.homework.order.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(description = "주문 정보")
public class OrderDto {

    private Long orderId;

    private String orderNo;

    private Long memberId;

    private String name;

    private String email;

    @Builder
    @QueryProjection
    public OrderDto(Long orderId,
                    String orderNo,
                    Long memberId,
                    String name,
                    String email) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

}
