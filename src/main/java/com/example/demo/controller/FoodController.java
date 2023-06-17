package com.example.demo.controller;
import com.example.demo.dto.request.FoodRequestDto;
import com.example.demo.dto.response.FoodResponseDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
@Slf4j
public class FoodController {
    private final FoodService foodService;

    @PostMapping
    public ResponseEntity<ResponseDto> createFood(@RequestBody FoodRequestDto foodRequestDto) {
        log.info("Creating a new food");
        ResponseEntity<ResponseDto> responseEntity = foodService.create(foodRequestDto);
        log.info("Food created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseEntity.getBody());
    }
    @GetMapping("/food/findAll")
    public ResponseEntity<List<FoodResponseDto>> getAllFoods() {
        log.info("Fetching all foods");
        List<FoodResponseDto> foods = foodService.findAll();
        log.info("Fetched all foods successfully");
        return ResponseEntity.ok(foods);}

    @GetMapping("/food/{id}")
    public ResponseEntity<FoodResponseDto> getFoodById(@PathVariable Long id) {
        log.info("Fetching food by ID: {}", id);
        FoodResponseDto food = foodService.findById(id);
        log.info("Fetched food by ID: {} successfully", id);
        return ResponseEntity.ok(food);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateFood(@PathVariable Long id, @RequestBody FoodRequestDto foodRequestDto) {
        log.info("Updating food with ID: {}", id);
        ResponseEntity<ResponseDto> responseEntity = foodService.update(foodRequestDto);
        log.info("Food with ID: {} updated successfully", id);
        return ResponseEntity.ok(responseEntity.getBody());
    }
}

