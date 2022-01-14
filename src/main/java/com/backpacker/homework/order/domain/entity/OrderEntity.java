package com.backpacker.homework.order.domain.entity;

import com.backpacker.homework.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@ToString
@Table(name = "order_master")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "payment_datetime")
    private LocalDateTime paymentDatetime;

}
