package com.nursery.nursery.service;

import com.nursery.nursery.dto.CategoryResponse;
import com.nursery.nursery.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();

    Category addCategory(Category category);

    void deleteCategory(Long id);
}