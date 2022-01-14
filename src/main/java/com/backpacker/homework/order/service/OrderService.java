package com.backpacker.homework.order.service;

import com.backpacker.homework.common.PagingOption;
import com.backpacker.homework.common.converter.PageResponseConverter;
import com.backpacker.homework.common.converter.PageableConverter;
import com.backpacker.homework.common.response.PageResponse;
import com.backpacker.homework.order.domain.repository.OrderRepository;
import com.backpacker.homework.order.dto.OrderDto;
import com.backpacker.homework.order.dto.OrderSearchOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public PageResponse<OrderDto> findOrderList(OrderSearchOption orderSearchOption,
                                                PagingOption pagingOption){
        Page<OrderDto> orderPage = orderRepository.findOrderList(orderSearchOption, PageableConverter.convert(pagingOption));
        return PageResponseConverter.convert(orderPage);
    }

}
