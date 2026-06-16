package com.nursery.nursery.controller;

import com.nursery.nursery.dto.FlowerResponse;
import com.nursery.nursery.response.ApiResponse;
import com.nursery.nursery.service.FlowerService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flowers")
@CrossOrigin("*")
public class FlowerController {

    private final FlowerService flowerService;

    public FlowerController(
            FlowerService flowerService
    ) {
        this.flowerService = flowerService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<FlowerResponse>>>
    getFlowers(

            @RequestParam(required = false)
            Long categoryId,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction
    ) {

        Page<FlowerResponse> flowers =
                flowerService.getFlowers(
                        categoryId,
                        page,
                        size,
                        sortBy,
                        direction
                );

        ApiResponse<Page<FlowerResponse>> response =
                new ApiResponse<>(
                        true,
                        "Flowers fetched successfully",
                        flowers
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FlowerResponse>>
    getFlowerById(
            @PathVariable Long id
    ) {

        FlowerResponse flower =
                flowerService.getFlowerById(id);

        ApiResponse<FlowerResponse> response =
                new ApiResponse<>(
                        true,
                        "Flower fetched successfully",
                        flower
                );

        return ResponseEntity.ok(response);
    }
}