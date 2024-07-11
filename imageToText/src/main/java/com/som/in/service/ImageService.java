package com.som.in.service;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String imageToTextService(MultipartFile file) throws IOException, TesseractException;
}
