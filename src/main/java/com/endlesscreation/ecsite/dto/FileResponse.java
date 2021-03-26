package com.endlesscreation.ecsite.dto;

public class FileResponse {

    private String filename;
    private String uri;
    private String fileType;
    private Long size;

    public FileResponse(String filename, String uri, String fileType, Long size) {
        this.filename = filename;
        this.uri = uri;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public String getUri() {
        return uri;
    }

    public String getFileType() {
        return fileType;
    }

    public Long getSize() {
        return size;
    }
}
