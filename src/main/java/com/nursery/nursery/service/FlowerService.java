package com.nursery.nursery.service;

import com.nursery.nursery.dto.FlowerRequest;
import com.nursery.nursery.dto.FlowerResponse;
import com.nursery.nursery.entity.Flower;
import org.springframework.data.domain.Page;

public interface FlowerService {

    Flower addFlower(FlowerRequest request);

    Flower updateFlower(
            Long id,
            FlowerRequest request
    );

    void deleteFlower(Long id);

    Page<FlowerResponse> getFlowers(
            Long categoryId,
            int page,
            int size,
            String sortBy,
            String direction
    );

    FlowerResponse getFlowerById(Long id);
}