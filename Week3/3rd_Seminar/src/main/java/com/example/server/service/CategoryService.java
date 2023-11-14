package com.example.server.service;

import com.example.server.domain.Category;
import com.example.server.domain.CategoryId;
import com.example.server.dto.category.CategoryResponse;
import com.example.server.repository.CategoryJPARepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryJPARepository categoryJPARepository;

    public Category getCategoryById(CategoryId categoryId) {
        return categoryJPARepository.findById(Short.valueOf(categoryId.getCategoryId())).orElseThrow(
                () -> new EntityNotFoundException("해당하는 카테고리가 없습니다.")
        );
    }

    public CategoryResponse getById(Short id) {
        return CategoryResponse.of(categoryJPARepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("해당하는 카테고리가 없습니다.")
        ));
    }
}
