package com.endlesscreation.ecsite.service;

import com.endlesscreation.ecsite.util.FileStorage;
import com.endlesscreation.ecsite.util.ImageExtension;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ImageService {

    private static final String IMAGE_TYPE = "image";

    private final FileStorage fileStorage;

    public String uploadMultipartFile(MultipartFile imageFile) {
        validateImage(imageFile);
        return fileStorage.store(imageFile);
    }

    private void validateImage(MultipartFile multipartFile) {
        ImageExtension.validateImageExtension(multipartFile);
        validateImageMimeType(multipartFile);
    }

    private void validateImageMimeType(MultipartFile multipartFile) {
        try {
            if (isNotImageMimeType(multipartFile)) {
                throw new IllegalArgumentException(
                    multipartFile.getName() + " 파일은 이미지 MIME 타입이 아닙니다!");
            }
        } catch (IOException e) {
            throw new IllegalStateException(
                multipartFile.getName() + "의 바이트 파일을 가져오는데 실패했습니다!");
        }
    }

    private boolean isNotImageMimeType(MultipartFile multipartFile) throws IOException {
        Tika tika = new Tika();
        return !tika.detect(multipartFile.getBytes())
            .startsWith(IMAGE_TYPE);
    }
}
