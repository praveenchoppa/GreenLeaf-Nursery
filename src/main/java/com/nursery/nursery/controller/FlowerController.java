package com.nursery.nursery.controller;

import com.nursery.nursery.entity.Flower;
import com.nursery.nursery.repository.FlowerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flowers")
@CrossOrigin("*")
public class FlowerController {

    private final FlowerRepository flowerRepository;

    public FlowerController(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    @GetMapping
    public List<Flower> getFlowers(@RequestParam(required = false) Long categoryId) {

        if (categoryId != null) {
            return flowerRepository.findByCategoryId(categoryId);
        }

        return flowerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Flower getFlowerById(@PathVariable Long id) {
        return flowerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Flower not found with id: " + id)
        );
    }
}
