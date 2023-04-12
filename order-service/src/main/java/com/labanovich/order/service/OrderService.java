package com.labanovich.order.service;

import com.labanovich.order.dto.OrderCreateDto;
import com.labanovich.order.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> findAll();

    OrderDto createOrder(OrderCreateDto orderCreateDto);
}
