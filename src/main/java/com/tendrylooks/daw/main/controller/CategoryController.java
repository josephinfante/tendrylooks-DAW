package com.tendrylooks.daw.main.controller;

import com.tendrylooks.daw.main.dto.*;
import com.tendrylooks.daw.main.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> createProduct(@RequestBody CategoryCreateDto categoryCreateDto) {
        try {
            categoryService.createCategory(categoryCreateDto);
            return ResponseEntity.ok(new ApiResponseDto("Category created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error creating category: " + e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto> updateCategory(@RequestBody CategoryDetailDto categoryDetailDto) {
        try {
            categoryService.updateCategory(categoryDetailDto);
            return ResponseEntity.ok(new ApiResponseDto("Category updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error updating category: " + e.getMessage()));
        }
    }

    @GetMapping("/{codCat}")
    public ResponseEntity findById(@PathVariable Integer codCat) {
        try {
            CategoryDetailDto categoryDetail = categoryService.findById(codCat);
            return ResponseEntity.ok(categoryDetail);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(new ApiResponseDto(ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error getting the category: " + e.getMessage()));
        }
    }

    @GetMapping
    public GetAllResponseDto<CategoryDto> getAllCategories(@RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<CategoryDto> categoryPage = categoryService.getAllCategories(pageable);
        return new GetAllResponseDto<>(
                categoryPage.getContent(),
                categoryPage.getSize(),
                categoryPage.getNumber() + 1,
                categoryPage.getTotalElements(),
                categoryPage.getTotalPages()
        );
    }
}
