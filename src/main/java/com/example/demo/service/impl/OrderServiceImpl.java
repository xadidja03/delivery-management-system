package com.example.demo.service.impl;
import com.example.demo.dto.request.OrderRequestDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.config.EntityMapper;
import com.example.demo.dto.response.OrderResponseDto;
import com.example.demo.entity.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final EntityMapper entityMapper;

    @Override
    public List<OrderResponseDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(entityMapper::orderToOrderDtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
        return entityMapper.orderToOrderDtoResponse(order);
    }



    @Override
    public ResponseEntity<ResponseDto> update(OrderRequestDto orderRequestDto) {
        Long orderId = orderRequestDto.getId();
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

        entityMapper.updateOrderFromDtoRequest(orderRequestDto, existingOrder);
        Order updatedOrder = orderRepository.save(existingOrder);
        ResponseDto responseDto = new ResponseDto("Order updated successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
