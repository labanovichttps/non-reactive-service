package com.labanovich.order.controller;

import com.labanovich.order.dto.OrderCreateDto;
import com.labanovich.order.dto.OrderDto;
import com.labanovich.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> findAll() {
        return ResponseEntity.ok(
            orderService.findAll());
    }

    @PostMapping
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderCreateDto orderCreateDto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(orderService.createOrder(orderCreateDto));
    }
}
