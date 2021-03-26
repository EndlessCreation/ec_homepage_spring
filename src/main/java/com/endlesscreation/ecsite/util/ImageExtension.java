package com.endlesscreation.ecsite.util;

import java.util.Arrays;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public enum ImageExtension {
    APNG(".apng"),
    GIF(".gif"),
    ICO(".ico"),
    CUR(".cur"),
    JPG(".jpg"),
    JPEG(".jpeg"),
    JFIF(".jfif"),
    PJPEG(".pjpeg"),
    PJP(".pjp"),
    PNG(".png"),
    SVG(".svg"),
    TIF(".tif"),
    TIFF(".tiff"),
    WEBP(".webp");

    private final String extension;

    ImageExtension(String extension) {
        this.extension = extension;
    }

    public static void validateImageExtension(MultipartFile file) {
        if (noneMatchImageExtension(file)) {
            throw new IllegalArgumentException(
                file.getOriginalFilename() + "은/는 이미지 파일에 맞는 확장자가 아닙니다!");
        }
    }

    private static boolean noneMatchImageExtension(MultipartFile file) {
        return Arrays.stream(ImageExtension.values())
            .noneMatch(imageExtension -> isSame(file, imageExtension.extension));
    }

    private static boolean isSame(MultipartFile file, String extension) {
        return Objects.requireNonNull(file.getOriginalFilename())
            .toLowerCase()
            .endsWith(extension);
    }
}