package com.nursery.nursery.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nursery.nursery.dto.FlowerRequest;
import com.nursery.nursery.entity.Category;
import com.nursery.nursery.entity.Flower;
import com.nursery.nursery.repository.CategoryRepository;
import com.nursery.nursery.repository.FlowerRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private final CategoryRepository categoryRepository;
    private final FlowerRepository flowerRepository;
    private final Cloudinary cloudinary;

    public AdminController(
            CategoryRepository categoryRepository,
            FlowerRepository flowerRepository,
            Cloudinary cloudinary
    ) {
        this.categoryRepository = categoryRepository;
        this.flowerRepository = flowerRepository;
        this.cloudinary = cloudinary;
    }

    // CLOUDINARY for image upload
    @PostMapping("/upload")
    public String uploadImage(
            @RequestParam("file") MultipartFile file
    ) throws Exception {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("folder", "nursery_flowers")
        );

        return uploadResult.get("secure_url").toString();
    }

    @PostMapping("/categories")
    public Category addCategory(
            @RequestBody Category category
    ) {

        return categoryRepository.save(category);
    }

    @DeleteMapping("/categories/{id}")
public String deleteCategory(
        @PathVariable Long id
) {

    //  Delete flowers first
    flowerRepository.deleteAll(
            flowerRepository.findByCategoryId(id)
    );

    //  Then delete category
    categoryRepository.deleteById(id);

    return "Category deleted successfully";
}

    @PostMapping("/flowers")
    public Flower addFlower(
            @Valid @RequestBody FlowerRequest req
    ) {

        Category category =
                categoryRepository.findById(req.categoryId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Category not found: "
                                                + req.categoryId
                                )
                        );

        Flower flower = new Flower();

        flower.setName(req.name);
        flower.setPrice(req.price);
        flower.setWeight(req.weight);
        flower.setPotSize(req.potSize);
        flower.setDescription(req.description);
        flower.setImageUrl(req.imageUrl);
        flower.setCategory(category);

        return flowerRepository.save(flower);
    }

    @PutMapping("/flowers/{id}")
    public Flower updateFlower(
            @PathVariable Long id,
            @Valid @RequestBody FlowerRequest req
    ) {

        Flower existing =  flowerRepository.findById(id)
                           .orElseThrow(() -> new RuntimeException(
                                        "Flower not found: " + id
                                )
                        );

        Category category = categoryRepository.findById(req.categoryId)
                        .orElseThrow(() -> new RuntimeException(
                                        "Category not found: "+ req.categoryId
                                )
                        );

        existing.setName(req.name);
        existing.setPrice(req.price);
        existing.setWeight(req.weight);
        existing.setPotSize(req.potSize);
        existing.setDescription(req.description);
        existing.setImageUrl(req.imageUrl);
        existing.setCategory(category);

        return flowerRepository.save(existing);
    }

    @DeleteMapping("/flowers/{id}")
    public String deleteFlower(
            @PathVariable Long id
    ) {

        flowerRepository.deleteById(id);

        return "Flower deleted";
    }
}