package com.example.demo.service;
import com.example.demo.dto.request.OrderRequestDto;
import com.example.demo.dto.response.OrderResponseDto;
import com.example.demo.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface OrderService {
    List<OrderResponseDto> findAll();
    OrderResponseDto findById(Long id);
    ResponseEntity<ResponseDto> update(OrderRequestDto orderRequestDto);

}
