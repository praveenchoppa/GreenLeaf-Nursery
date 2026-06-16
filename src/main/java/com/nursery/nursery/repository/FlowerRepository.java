package com.nursery.nursery.repository;

import com.nursery.nursery.entity.Flower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowerRepository
        extends JpaRepository<Flower, Long> {

  
    Page<Flower> findByCategoryId(
            Long categoryId,
            Pageable pageable
    );


    List<Flower> findByCategoryId(
            Long categoryId
    );
}