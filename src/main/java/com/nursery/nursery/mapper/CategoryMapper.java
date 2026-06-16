package com.nursery.nursery.mapper;

import com.nursery.nursery.dto.CategoryResponse;
import com.nursery.nursery.entity.Category;

public class CategoryMapper {

    private CategoryMapper() {
    }

    public static CategoryResponse toResponse(
            Category category
    ) {

        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }
}