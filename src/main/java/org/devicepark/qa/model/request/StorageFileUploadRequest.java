package org.devicepark.qa.model.request;

public class StorageFileUploadRequest {

    private final String filePath;
    private final String fileType;

    public StorageFileUploadRequest(String filePath, String fileType) {
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFilePath() {
        return filePath;
    }
}