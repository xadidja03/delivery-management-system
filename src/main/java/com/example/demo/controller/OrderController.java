package com.example.demo.controller;
import com.example.demo.dto.request.OrderRequestDto;
import com.example.demo.dto.response.OrderResponseDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

//    @PostMapping
//    public ResponseEntity<ResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
//        log.info("Creating order: {}", orderRequestDto);
//        ResponseEntity<ResponseDto> responseEntity = orderService.create(orderRequestDto);
//        log.info("Order created successfully: {}", responseEntity.getBody());
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseEntity.getBody());
//    }

    @GetMapping("/orders/findAll")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        log.info("Fetching all orders");
        List<OrderResponseDto> orders = orderService.findAll();
        log.info("Fetched {} orders", orders.size());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        log.info("Fetching order with ID: {}", id);
        OrderResponseDto order = orderService.findById(id);
        log.info("Fetched order with ID {}: {}", id, order);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateOrder(@PathVariable Long id, @RequestBody OrderRequestDto orderRequestDto) {
        log.info("Updating order with ID: {}", id);
        ResponseEntity<ResponseDto> responseEntity = orderService.update(orderRequestDto);
        log.info("Order with ID {} updated successfully: {}", id, responseEntity.getBody());
        return ResponseEntity.ok(responseEntity.getBody());
    }

}
