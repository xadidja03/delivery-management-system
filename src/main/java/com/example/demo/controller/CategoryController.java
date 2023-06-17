package com.example.demo.controller;
import com.example.demo.dto.request.CategoryRequestDto;
import com.example.demo.dto.response.CategoryResponseDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j

public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/registration")
    public ResponseEntity<ResponseDto> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        log.info("Creating a new category: {}", categoryRequestDto);
        ResponseEntity<ResponseDto> responseEntity = categoryService.create(categoryRequestDto);
        log.info("Category created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseEntity.getBody());
    }

    @GetMapping("/categories/findAll")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        log.info("Fetching all categories");
        List<CategoryResponseDto> category = categoryService.findAll();
        log.info("Categories fetched successfully");
        return ResponseEntity.ok(category);
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long id) {
        log.info("Fetching category with ID: {}", id);
        CategoryResponseDto service = categoryService.findById(id);
        log.info("Category fetched successfully");
        return ResponseEntity.ok(service);
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<ResponseDto> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto) {
        log.info("Updating category with ID: {}", id);
        ResponseEntity<ResponseDto> responseEntity = categoryService.update(categoryRequestDto);
        log.info("Category updated successfully");
        return ResponseEntity.ok(responseEntity.getBody());
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<ResponseDto> deleteCategory(@PathVariable Long id) {
        log.info("Deleting category with ID: {}", id);
        ResponseEntity<ResponseDto> responseEntity = categoryService.delete(id);
        log.info("Category deleted successfully");
        return ResponseEntity.ok(responseEntity.getBody());
    }
}

