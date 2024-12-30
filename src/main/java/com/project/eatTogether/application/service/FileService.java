package com.project.eatTogether.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@Slf4j
public class FileService {

    private final String uploadBaseDir;

    public FileService(@Value("${com.project.upload.path}") String uploadBaseDir) {
        this.uploadBaseDir = uploadBaseDir;
    }

    public String saveFile(MultipartFile file, String directory) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path uploadPath = Paths.get(uploadBaseDir, directory);

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                return fileName;
            }
        } catch (IOException e) {
            log.error("파일 저장 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
    }

    public String getFilePath(String fileName) {
        return Paths.get(uploadBaseDir, fileName).toString();
    }

    // 파일 전체 경로 (디렉토리 + 파일명)를 얻기 위한 메서드 추가
    public String getFullPath(String directory, String fileName) {
        return Paths.get(uploadBaseDir, directory, fileName).toString();
    }
}
