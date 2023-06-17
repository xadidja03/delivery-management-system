package com.example.demo.service;
import com.example.demo.dto.request.CategoryRequestDto;
import com.example.demo.dto.response.CategoryResponseDto;
import com.example.demo.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> findAll();
    CategoryResponseDto findById(Long id);

    ResponseEntity<ResponseDto> create(CategoryRequestDto categoryRequestDto);
    ResponseEntity<ResponseDto> update(CategoryRequestDto categoryRequestDto);
    ResponseEntity<ResponseDto> delete(Long id);
}
