package com.som.in.serviceImp;

import com.som.in.service.ImageService;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageServiceImp implements ImageService {
    @Value("${tessdata.path}")
    private String tessdataPath;
    @Override
    public String imageToTextService(MultipartFile file) throws IOException, TesseractException {
        File image = convertMultiPartToFile(file);
        String absoluteTessdataPath = Paths.get(tessdataPath).toAbsolutePath().toString();
        System.out.println("Tesseract data path: " + absoluteTessdataPath);
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(absoluteTessdataPath);
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        String result = tesseract.doOCR(image);
        return result;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            fileName = "tempfile";
        }
        File convertedFile = new File(fileName);
        Files.copy(file.getInputStream(), convertedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return convertedFile;
    }
}
