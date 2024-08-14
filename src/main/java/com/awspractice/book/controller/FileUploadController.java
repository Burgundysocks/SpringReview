package com.awspractice.book.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {

    private static final String UPLOAD_DIR = "src/main/resources/static/assets/images/upload/";

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "File is empty";
        }

        Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        Files.write(path, file.getBytes());

        // 반환하는 경로를 클라이언트가 접근 가능한 URL로 변환
        return "/assets/images/upload/" + file.getOriginalFilename();
    }

}
