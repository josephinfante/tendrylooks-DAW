package com.tendrylooks.daw.main.service.impl;

import com.tendrylooks.daw.main.dto.CategoryCreateDto;
import com.tendrylooks.daw.main.dto.CategoryDetailDto;
import com.tendrylooks.daw.main.dto.CategoryDto;
import com.tendrylooks.daw.main.entity.Category;
import com.tendrylooks.daw.main.repository.CategoryRepository;
import com.tendrylooks.daw.main.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryCreateDto categoryCreateDto) {
        categoryRepository.createCategory(
                categoryCreateDto.nomCat()
        );
    }

    @Override
    public void updateCategory(CategoryDetailDto categoryDetailDto) {
        Category existingCategory = categoryRepository.findById(categoryDetailDto.codCat())
                .orElseThrow(() -> new EntityNotFoundException("Category not found for ID: " + categoryDetailDto.codCat()));

        if (categoryDetailDto.nomCat() != null) {
            existingCategory.setNomCat(categoryDetailDto.nomCat());
        }
        if (categoryDetailDto.estCat() != null) {
            existingCategory.setEstCat(categoryDetailDto.estCat());
        }
        if (categoryDetailDto.fecCat() != null) {
            existingCategory.setFecCat(categoryDetailDto.fecCat());
        }

        categoryRepository.save(existingCategory);
    }

    @Override
    public CategoryDetailDto findById(Integer codCat) {
        Category category = categoryRepository.findById(codCat)
                .orElseThrow(() -> new EntityNotFoundException("Category not found for ID: " + codCat));

        return new CategoryDetailDto(
                category.getCodCat(),
                category.getNomCat(),
                category.getEstCat(),
                category.getFecCat()
        );
    }

    @Override
    public Page<CategoryDto> getAllCategories(Pageable pageable) {
        int limit = pageable.getPageSize();
        int offset = (int) pageable.getOffset();

        List<Category> categories = categoryRepository.spGetAllCategories(limit, offset);

        List<CategoryDto> categoryDto = categories.stream().map(this::convertToDto).collect(Collectors.toList());

        long totalCategories = categoryRepository.count();

        return new PageImpl<>(categoryDto, pageable, totalCategories);
    }

    private CategoryDto convertToDto(Category category) {
        return new CategoryDto(
                category.getCodCat(),
                category.getNomCat(),
                category.getEstCat()
        );
    }
}
