package com.example.demo.service.impl;
import com.example.demo.config.EntityMapper;
import com.example.demo.dto.response.FoodResponseDto;
import com.example.demo.exception.FoodNotFoundException;
import com.example.demo.dto.request.FoodRequestDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.model.Food;
import com.example.demo.repository.FoodRepository;
import com.example.demo.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final EntityMapper entityMapper;
    @Override
    public ResponseEntity<ResponseDto> create(FoodRequestDto foodRequestDto) {
        Food food = entityMapper.foodDtoRequestToFood(foodRequestDto);
        Food createdFood = foodRepository.save(food);
        ResponseDto responseDto = new ResponseDto("Food created successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @Override
    public List<FoodResponseDto> findAll() {
        List<Food> foods = foodRepository.findAll();
        List<FoodResponseDto> foodResponsDtos = foods.stream()
                .map(entityMapper::foodToFoodDtoResponse)
                .collect(Collectors.toList());
        return foodResponsDtos;
    }

    @Override
    public FoodResponseDto findById(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(FoodNotFoundException::new);
        return entityMapper.foodToFoodDtoResponse(food);
    }



    @Override
    public ResponseEntity<ResponseDto> update(FoodRequestDto foodRequestDto) {
        Long foodId = foodRequestDto.getId();
        Food existingFood = foodRepository.findById(foodId)
                .orElseThrow(FoodNotFoundException::new);

        entityMapper.updateFoodFromDtoRequest(foodRequestDto, existingFood);
        Food updatedFood = foodRepository.save(existingFood);
        ResponseDto responseDto = new ResponseDto("Food updated successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
