package com.backpacker.homework.order.domain.repository;

import com.backpacker.homework.order.dto.OrderDto;
import com.backpacker.homework.order.dto.OrderSearchOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {

    Page<OrderDto> findOrderList(OrderSearchOption orderSearchOption, Pageable pageable);

}
