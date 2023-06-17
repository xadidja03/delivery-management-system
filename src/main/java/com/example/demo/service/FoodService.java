package com.example.demo.service;
import com.example.demo.dto.request.FoodRequestDto;
import com.example.demo.dto.response.FoodResponseDto;
import com.example.demo.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface FoodService {
    List<FoodResponseDto> findAll();
    FoodResponseDto findById(Long id);

    ResponseEntity<ResponseDto> create(FoodRequestDto foodRequestDto);

    ResponseEntity<ResponseDto> update(FoodRequestDto foodRequestDto);
}