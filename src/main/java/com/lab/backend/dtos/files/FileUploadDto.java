package com.lab.backend.dtos.files;

public class FileUploadDto {
    private String filename;
    private String downloadUrl;

    public FileUploadDto() {
    }

    public FileUploadDto(String filename, String downloadUrl) {
        this.filename = filename;
        this.downloadUrl = downloadUrl;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
