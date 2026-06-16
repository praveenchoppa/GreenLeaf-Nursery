package com.nursery.nursery.service.impl;

import com.nursery.nursery.dto.CategoryResponse;
import com.nursery.nursery.entity.Category;
import com.nursery.nursery.mapper.CategoryMapper;
import com.nursery.nursery.repository.CategoryRepository;
import com.nursery.nursery.repository.FlowerRepository;
import com.nursery.nursery.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl
        implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FlowerRepository flowerRepository;

    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            FlowerRepository flowerRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.flowerRepository = flowerRepository;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    @Override
    public Category addCategory(
            Category category
    ) {

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {

        flowerRepository.deleteAll(
                flowerRepository.findByCategoryId(id)
        );

        categoryRepository.deleteById(id);
    }
}