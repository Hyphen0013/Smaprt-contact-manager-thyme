package com.binary.services;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryImageService {

    String uploadImageToCloudinary(MultipartFile contactImage, String filename);
    String getUrlFromPublicId(String publicId);
    boolean deleteImageFromCloudinary(String publicId);
}
