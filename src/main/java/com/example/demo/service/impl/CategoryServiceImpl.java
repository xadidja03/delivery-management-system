package com.example.demo.service.impl;

import com.example.demo.config.EntityMapper;
import com.example.demo.dto.request.CategoryRequestDto;
import com.example.demo.dto.response.CategoryResponseDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.model.Category;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.FoodRepository;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final FoodRepository foodRepository;
    private final EntityMapper entityMapper;

    @Override
    public ResponseEntity<ResponseDto> create(CategoryRequestDto categoryRequestDto) {
        Category category = entityMapper.categoryDtoRequestToCategory(categoryRequestDto);
        Category createdCategory = categoryRepository.save(category);
        ResponseDto responseDto = new ResponseDto("Category created successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponsDtos = categories.stream()
                .map(entityMapper::categoryToCategoryDtoResponse)
                .collect(Collectors.toList());
        return categoryResponsDtos;
    }

//    @Override
//    public List<FoodResponseDto> findAllFood(Long id) {
//        List<Food> foods= foodRepository.findAllById(Collections.singleton(id));
//        List<FoodResponseDto> foodDtoResponses=foods.stream()
//                .map(entityMapper::foodToFoodDtoResponse)
//                .collect(Collectors.toList()
//                );
//        return foodDtoResponses;
//    }

    @Override
    public CategoryResponseDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        return entityMapper.categoryToCategoryDtoResponse(category);
    }

    @Override
    public ResponseEntity<ResponseDto> update(CategoryRequestDto categoryRequestDto) {
        Long categoryId = categoryRequestDto.getId();
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        entityMapper.updateCategoryFromDtoRequest(categoryRequestDto, existingCategory);
        Category updatedCategory = categoryRepository.save(existingCategory);
        ResponseDto responseDto = new ResponseDto("Category updated successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto> delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(category);
        ResponseDto responseDto = new ResponseDto("Category deleted successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
