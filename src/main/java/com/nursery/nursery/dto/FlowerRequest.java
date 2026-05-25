package com.nursery.nursery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public class FlowerRequest {

    @NotNull(message = "Category is required")
    public Long categoryId;

    @NotBlank(message = "Flower name is required")
    public String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.1", message = "Price must be positive")
    public BigDecimal price;

    public Double weight;

    @NotBlank(message = "Pot size is required")
    public String potSize;

    public String description;

    @NotBlank(message = "Image URL is required")
    public String imageUrl;
}