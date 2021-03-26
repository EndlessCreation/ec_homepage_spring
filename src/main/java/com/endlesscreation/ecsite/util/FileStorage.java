package com.endlesscreation.ecsite.util;

import com.endlesscreation.ecsite.config.FileStorageProperties;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStorage {

    private static final String FILE_NAME_DELIMITER = "_";

    private final Path fileStorageLocation;

    public FileStorage(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
            .toAbsolutePath()
            .normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new IllegalStateException("이미지 저장 디렉토리를 만들 수 없음", e);
        }
    }

    public String store(MultipartFile multipartFile) {
        String fileName = UUID.randomUUID().toString()
            + FILE_NAME_DELIMITER
            + multipartFile.getOriginalFilename();
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            throw new IllegalStateException("파일 업로드 실패 - 파일이름 : " + fileName);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            }
            throw new IllegalArgumentException("file not found - 파일이름 : " + fileName);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("파일읽기 실패 - 파일이름 : " + fileName);
        }
    }
}
