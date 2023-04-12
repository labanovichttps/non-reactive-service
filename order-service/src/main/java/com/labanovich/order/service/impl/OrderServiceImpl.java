package com.labanovich.order.service.impl;

import com.labanovich.order.dto.ProductDto;
import com.labanovich.order.dto.UserDto;
import com.labanovich.order.entity.Order;
import com.labanovich.order.mapper.OrderMapper;
import com.labanovich.order.client.ProductClient;
import com.labanovich.order.client.UserClient;
import com.labanovich.order.dto.OrderCreateDto;
import com.labanovich.order.dto.OrderDto;
import com.labanovich.order.repository.OrderRepository;
import com.labanovich.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserClient userClient;
    private final ProductClient productClient;


    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderDto)
                .toList();
    }

    @Override
    public OrderDto createOrder(OrderCreateDto orderCreateDto) {
        ProductDto product = productClient.getProduct(orderCreateDto.productId());
        UserDto user = userClient.getUser(orderCreateDto.userId());
        Order order = orderMapper.toOrder(user, product);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderDto(savedOrder);
    }
}
