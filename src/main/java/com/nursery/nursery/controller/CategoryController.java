package com.nursery.nursery.controller;

import com.nursery.nursery.dto.CategoryResponse;
import com.nursery.nursery.response.ApiResponse;
import com.nursery.nursery.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(
            CategoryService categoryService
    ) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>>
    getAllCategories() {

        return new ApiResponse<>(
                true,
                "Categories fetched successfully",
                categoryService.getAllCategories()
        );
    }
}