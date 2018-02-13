package com.cat.util;

import com.cat.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

public interface ImageService {
    public Image find(Long id);

    public Image save(String imageName, String contentType, byte[] fileContent);
    
    public Image save(String imageName, MultipartFile file);

    public BufferedImage scale(final BufferedImage image, int maxWidth, int maxHeight);
}