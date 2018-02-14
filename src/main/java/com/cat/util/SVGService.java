package com.cat.util;

import com.cat.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

public interface SVGService {
    public Image load(Long id);
}