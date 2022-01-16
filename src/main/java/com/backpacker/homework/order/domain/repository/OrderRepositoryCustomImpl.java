package com.backpacker.homework.order.domain.repository;

import com.backpacker.homework.order.domain.entity.OrderEntity;
import com.backpacker.homework.order.dto.OrderDto;
import com.backpacker.homework.order.dto.OrderSearchOption;
import com.backpacker.homework.order.dto.QOrderDto;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;
import java.util.List;
import static com.backpacker.homework.order.domain.entity.QOrderEntity.orderEntity;
import static com.backpacker.homework.member.domian.entity.QMemberEntity.memberEntity;

public class OrderRepositoryCustomImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {

    public OrderRepositoryCustomImpl() {
       super(OrderEntity.class);
    }

    @Override
    public Page<OrderDto> findOrderMemberList(Long memberId, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());

        JPAQuery<OrderDto> query = queryFactory.select(
                new QOrderDto(
                        orderEntity.orderId.as("orderId"),
                        orderEntity.orderNo.as("orderNo"),
                        orderEntity.memberId.as("memberId"),
                        memberEntity.name.as("name"),
                        memberEntity.email.as("email")
                )
        ).from(memberEntity)
            .innerJoin(orderEntity)
                .on(memberEntity.memberId.eq(orderEntity.memberId)
                .and(memberEntity.memberId.eq(memberId)));

        List<OrderDto> orderDtoList = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<>(orderDtoList, pageable, query.fetchCount());
    }

    @Override
    public Page<OrderDto> findOrderList(OrderSearchOption orderSearchOption, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());

        JPAQuery<OrderDto> query = queryFactory.select(
            new QOrderDto(
                    orderEntity.orderId.as("orderId"),
                    orderEntity.orderNo.as("orderNo"),
                    orderEntity.memberId.as("memberId"),
                    memberEntity.name.as("name"),
                    memberEntity.email.as("email")
            )
        ).from(orderEntity)
            .leftJoin(memberEntity)
                    .on(orderEntity.memberId.eq(memberEntity.memberId))
        .where(orderEntity.createdDateTime.in(
                JPAExpressions
                        .select(orderEntity.createdDateTime.max().as("createdDateTime"))
                        .from(orderEntity)
                        .groupBy(orderEntity.memberId)
        ));

        if(StringUtils.hasText(orderSearchOption.getName())){
            query.where(memberEntity.name.contains(orderSearchOption.getName()));
        }
        if(StringUtils.hasText(orderSearchOption.getEmail())){
            query.where(memberEntity.name.contains(orderSearchOption.getEmail()));
        }

        List<OrderDto> orderDtoList = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<>(orderDtoList, pageable, query.fetchCount());
    }

}
