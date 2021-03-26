package com.endlesscreation.ecsite.controller;

import com.endlesscreation.ecsite.dto.FileResponse;
import com.endlesscreation.ecsite.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(tags = {"4. 사진을 서버에 저장하고 이미지 URL을 내려받는 API"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @ApiOperation(value = "사진 업로드", notes = "사진을 업로드하고 이미지 URL을 내려받는다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "files", value = "form-data 형식 이미지파일", required = true)
    })
    @PostMapping
    public ResponseEntity<List<FileResponse>> upload(@RequestParam("files") MultipartFile[] files) {
        List<FileResponse> collect = Arrays.stream(files)
            .map(this::uploadMultipartFile)
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(collect);
    }

    private FileResponse uploadMultipartFile(MultipartFile file) {
        String filename = imageService.uploadMultipartFile(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/images/")
            .path(filename)
            .toUriString();
        return new FileResponse(filename, uri, file.getContentType(), file.getSize());
    }
}
