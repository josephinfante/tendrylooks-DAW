package com.tendrylooks.daw.main.service;

import com.tendrylooks.daw.main.dto.CategoryCreateDto;
import com.tendrylooks.daw.main.dto.CategoryDetailDto;
import com.tendrylooks.daw.main.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    void createCategory(CategoryCreateDto categoryCreateDto);
    void updateCategory(CategoryDetailDto categoryDetailDto);
    CategoryDetailDto findById(Integer codCat);
    Page<CategoryDto> getAllCategories(Pageable pageable);
}
