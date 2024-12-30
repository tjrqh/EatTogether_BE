package com.project.eatTogether.infrastructure.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
@Slf4j
@RequiredArgsConstructor
public class CustomFileUtil {

    @Value("${com.project.upload.path}")
    private String uploadPath;

    @PostConstruct
    public void init() {
        File tempFolder = new File(uploadPath);

        if (tempFolder.exists() == false) {
            tempFolder.mkdirs();
        }

        uploadPath = tempFolder.getAbsolutePath();
        log.info("-------------------------------------------");
        log.info("Upload path: " + uploadPath);
    }

    public List<String> saveFiles(List<MultipartFile> files) throws RuntimeException {
        if(files == null || files.size() == 0) {
            return List.of();
        }

        List<String> uploadNames = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            String savedName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
            Path savedPath = Paths.get(uploadPath, savedName);

            try{
                Files.copy(multipartFile.getInputStream(), savedPath);
                String contentType = multipartFile.getContentType();
                if (contentType != null && contentType.startsWith("image")) {
                    Path thumbnailPath = Paths.get(uploadPath, "s_"+savedName);

                    Thumbnails.of(savedPath.toFile())
                            .size(200,200)
                            .toFile(thumbnailPath.toFile());
                }
                uploadNames.add(savedName);
            }catch(IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }//end for
        return uploadNames;
    }

    public String saveFile(MultipartFile file) throws RuntimeException {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("파일이 비어있습니다.");
        }

        String savedName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path savedPath = Paths.get(uploadPath, savedName);

        try {
            Files.copy(file.getInputStream(), savedPath);
            String contentType = file.getContentType();
            if (contentType != null && contentType.startsWith("image")) {
                Path thumbnailPath = Paths.get(uploadPath, "s_" + savedName);

                Thumbnails.of(savedPath.toFile())
                        .size(200, 200)
                        .toFile(thumbnailPath.toFile());
            }
            return savedName; // 저장된 파일 이름 반환
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<Resource> getFile(String fileName) {
        Resource resource = new FileSystemResource(uploadPath + File.separator+fileName);

        if(!resource.isReadable()) {
            resource = new FileSystemResource(uploadPath + File.separator+"default.jpeg");
        }

        HttpHeaders headers = new HttpHeaders();

        try{
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    public void deleteFiles(List<String> fileNames) {
        if (fileNames == null || fileNames.size() == 0) {
            return;
        }
        fileNames.forEach(fileName -> {
            //썸네일이 있는지 확인하고 삭제
            String thumbnailFileName = "s_" + fileName;
            Path thumbnailPath = Paths.get(uploadPath, thumbnailFileName);
            Path filePath = Paths.get(uploadPath, fileName);

            try{
                Files.deleteIfExists(filePath);
            }catch(IOException e){
                throw new RuntimeException(e.getMessage());
            }
        });
    }


}
