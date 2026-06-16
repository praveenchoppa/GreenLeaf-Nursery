package com.nursery.nursery.controller;

import com.nursery.nursery.dto.FlowerRequest;
import com.nursery.nursery.entity.Category;
import com.nursery.nursery.entity.Flower;
import com.nursery.nursery.response.ApiResponse;
import com.nursery.nursery.service.CategoryService;
import com.nursery.nursery.service.CloudinaryService;
import com.nursery.nursery.service.FlowerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private final CategoryService categoryService;
    private final FlowerService flowerService;
    private final CloudinaryService cloudinaryService;

    public AdminController(
            CategoryService categoryService,
            FlowerService flowerService,
            CloudinaryService cloudinaryService
    ) {
        this.categoryService = categoryService;
        this.flowerService = flowerService;
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<String>>
    uploadImage(
            @RequestParam("file")
            MultipartFile file
    ) throws Exception {

        String imageUrl =
                cloudinaryService.uploadImage(file);

        ApiResponse<String> response =
                new ApiResponse<>(
                        true,
                        "Image uploaded successfully",
                        imageUrl
                );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/categories")
    public ResponseEntity<ApiResponse<Category>>
    addCategory(
            @RequestBody Category category
    ) {

        Category savedCategory =
                categoryService.addCategory(category);

        ApiResponse<Category> response =
                new ApiResponse<>(
                        true,
                        "Category added successfully",
                        savedCategory
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<ApiResponse<Void>>
    deleteCategory(
            @PathVariable Long id
    ) {

        categoryService.deleteCategory(id);

        ApiResponse<Void> response =
                new ApiResponse<>(
                        true,
                        "Category deleted successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/flowers")
    public ResponseEntity<ApiResponse<Flower>>
    addFlower(
            @Valid @RequestBody
            FlowerRequest request
    ) {

        Flower flower =
                flowerService.addFlower(request);

        ApiResponse<Flower> response =
                new ApiResponse<>(
                        true,
                        "Flower added successfully",
                        flower
                );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/flowers/{id}")
    public ResponseEntity<ApiResponse<Flower>>
    updateFlower(
            @PathVariable Long id,
            @Valid @RequestBody
            FlowerRequest request
    ) {

        Flower updatedFlower =
                flowerService.updateFlower(
                        id,
                        request
                );

        ApiResponse<Flower> response =
                new ApiResponse<>(
                        true,
                        "Flower updated successfully",
                        updatedFlower
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/flowers/{id}")
    public ResponseEntity<ApiResponse<Void>>
    deleteFlower(
            @PathVariable Long id
    ) {

        flowerService.deleteFlower(id);

        ApiResponse<Void> response =
                new ApiResponse<>(
                        true,
                        "Flower deleted successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }
}