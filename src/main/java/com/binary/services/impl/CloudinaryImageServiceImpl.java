package com.binary.services.impl;

import java.io.IOException;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.binary.helpers.AppConstants;
import com.binary.services.CloudinaryImageService;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryImageServiceImpl implements CloudinaryImageService {

    private Cloudinary cloudinary;

    public CloudinaryImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    // THIS METHOD FOR UPLOAD IMAGE ON CLOUDINARY
    @Override
    public String uploadImageToCloudinary(MultipartFile contactImage, String filename) {
        try {
            byte[] data = new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(data);

            cloudinary.uploader().upload(data, ObjectUtils.asMap("public_id", filename));

            return this.getUrlFromPublicId(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // DELETE IMAGE FROM CLOUDINARY
    @Override
    public boolean deleteImageFromCloudinary(String publicId) {
        try {
            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            // result.get("result") should be "ok" if successful
            return "ok".equals(result.get("result"));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary
                .url()
                .transformation(
                        new Transformation<>()
                                .width(AppConstants.CONTACT_IMAGE_WIDTH)
                                .height(AppConstants.CONTACT_IMAGE_HEIGHT)
                                .crop(AppConstants.CONTACT_IMAGE_CROP))
                .generate(publicId);
    }

}
