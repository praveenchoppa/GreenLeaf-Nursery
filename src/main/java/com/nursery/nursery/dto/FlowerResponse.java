package com.nursery.nursery.dto;

import java.math.BigDecimal;

public class FlowerResponse {

    private Long id;
    private String name;
    private BigDecimal price;
    private Double weight;
    private String potSize;
    private String description;
    private String imageUrl;
    private Long categoryId;
    private String categoryName;

    public FlowerResponse(
            Long id,
            String name,
            BigDecimal price,
            Double weight,
            String potSize,
            String description,
            String imageUrl,
            Long categoryId,
            String categoryName
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.potSize = potSize;
        this.description = description;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Double getWeight() {
        return weight;
    }

    public String getPotSize() {
        return potSize;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}