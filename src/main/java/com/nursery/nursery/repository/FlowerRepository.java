package com.nursery.nursery.repository;

import com.nursery.nursery.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowerRepository
        extends JpaRepository<Flower, Long> {

    List<Flower> findByCategoryId(Long categoryId);
}