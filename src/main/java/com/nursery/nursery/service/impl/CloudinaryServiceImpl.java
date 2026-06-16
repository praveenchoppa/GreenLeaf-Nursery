package com.nursery.nursery.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nursery.nursery.exception.BadRequestException;
import com.nursery.nursery.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryServiceImpl
        implements CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(
            Cloudinary cloudinary
    ) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(
            MultipartFile file
    ) throws Exception {

        if (file == null || file.isEmpty()) {

            throw new BadRequestException(
                    "File is empty"
            );
        }

        Map<String, Object> uploadResult =
                cloudinary.uploader().upload(
                        file.getBytes(),
                        ObjectUtils.asMap(
                                "folder",
                                "nursery_flowers"
                        )
                );

        return uploadResult
                .get("secure_url")
                .toString();
    }
}