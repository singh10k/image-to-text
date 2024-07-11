package com.som.in.controller;

import com.som.in.service.ImageService;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value="/image/")
@RequiredArgsConstructor
public class ImageController {
    public final ImageService imageService;
    @PostMapping("/imageToText")
    public ResponseEntity<String> imageToTextController(@RequestPart("file") MultipartFile file) throws TesseractException, IOException {
        return ResponseEntity.ok(imageService.imageToTextService(file));
    }
}
