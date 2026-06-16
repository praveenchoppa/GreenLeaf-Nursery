package com.nursery.nursery.mapper;

import com.nursery.nursery.dto.FlowerResponse;
import com.nursery.nursery.entity.Flower;

public class FlowerMapper {

    private FlowerMapper() {
    }

    public static FlowerResponse toResponse(
            Flower flower
    ) {

        return new FlowerResponse(
                flower.getId(),
                flower.getName(),
                flower.getPrice(),
                flower.getWeight(),
                flower.getPotSize(),
                flower.getDescription(),
                flower.getImageUrl(),
                flower.getCategory().getId(),
                flower.getCategory().getName()
        );
    }
}