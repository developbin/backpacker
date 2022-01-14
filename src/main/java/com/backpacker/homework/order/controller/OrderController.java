package com.backpacker.homework.order.controller;

import com.backpacker.homework.common.PagingOption;
import com.backpacker.homework.common.response.PageResponse;
import com.backpacker.homework.generator.ApiResponseGenerator;
import com.backpacker.homework.order.dto.OrderDto;
import com.backpacker.homework.order.dto.OrderSearchOption;
import com.backpacker.homework.order.service.OrderService;
import com.backpacker.homework.common.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(tags = {"주문 API"})
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @ApiOperation(tags = "주문 API", value = "주문 정보", notes = "주문 정보를 노출합니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<OrderDto>>> findOrderList(@Valid @ModelAttribute OrderSearchOption orderSearchOption,
                                                                             @ModelAttribute PagingOption pagingOption){
        PageResponse<OrderDto> orderPageResponse = orderService.findOrderList(orderSearchOption, pagingOption);
        ApiResponse<PageResponse<OrderDto>> apiResponse = ApiResponseGenerator.success(orderPageResponse);
        return ResponseEntity.ok(apiResponse);
    }

}
