package com.nursery.nursery.service.impl;

import com.nursery.nursery.dto.FlowerRequest;
import com.nursery.nursery.dto.FlowerResponse;
import com.nursery.nursery.entity.Category;
import com.nursery.nursery.entity.Flower;
import com.nursery.nursery.exception.ResourceNotFoundException;
import com.nursery.nursery.mapper.FlowerMapper;
import com.nursery.nursery.repository.CategoryRepository;
import com.nursery.nursery.repository.FlowerRepository;
import com.nursery.nursery.service.FlowerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FlowerServiceImpl
        implements FlowerService {

    private final FlowerRepository flowerRepository;
    private final CategoryRepository categoryRepository;

    public FlowerServiceImpl(
            FlowerRepository flowerRepository,
            CategoryRepository categoryRepository
    ) {
        this.flowerRepository = flowerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Flower addFlower(
            FlowerRequest request
    ) {

        Category category =
                categoryRepository.findById(
                        request.getCategoryId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found"
                        )
                );

        Flower flower = new Flower();

        flower.setName(request.getName());
        flower.setPrice(request.getPrice());
        flower.setWeight(request.getWeight());
        flower.setPotSize(request.getPotSize());
        flower.setDescription(request.getDescription());
        flower.setImageUrl(request.getImageUrl());
        flower.setCategory(category);

        return flowerRepository.save(flower);
    }

    @Override
    public Flower updateFlower(
            Long id,
            FlowerRequest request
    ) {

        Flower existing =
                flowerRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Flower not found"
                                )
                        );

        Category category =
                categoryRepository.findById(
                        request.getCategoryId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found"
                        )
                );

        existing.setName(request.getName());
        existing.setPrice(request.getPrice());
        existing.setWeight(request.getWeight());
        existing.setPotSize(request.getPotSize());
        existing.setDescription(request.getDescription());
        existing.setImageUrl(request.getImageUrl());
        existing.setCategory(category);

        return flowerRepository.save(existing);
    }

    @Override
    public void deleteFlower(
            Long id
    ) {

        Flower flower =
                flowerRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Flower not found"
                                )
                        );

        flowerRepository.delete(flower);
    }

    @Override
    public Page<FlowerResponse> getFlowers(
            Long categoryId,
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        Sort sort =
                direction.equalsIgnoreCase("desc")
                        ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending();

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        sort
                );

        Page<Flower> flowers;

        if (categoryId != null) {

            flowers =
                    flowerRepository.findByCategoryId(
                            categoryId,
                            pageable
                    );
        } else {

            flowers =
                    flowerRepository.findAll(
                            pageable
                    );
        }

        return flowers.map(
                FlowerMapper::toResponse
        );
    }

    @Override
    public FlowerResponse getFlowerById(
            Long id
    ) {

        Flower flower =
                flowerRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Flower not found"
                                )
                        );

        return FlowerMapper.toResponse(
                flower
        );
    }
}