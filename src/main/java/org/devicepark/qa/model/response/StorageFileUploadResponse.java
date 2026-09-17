package org.devicepark.qa.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageFileUploadResponse {

    private long revision;
    private long sizeInBytes;
    private String version;
    private String fileKey;
    private String filePath;
    private String createdAt;
    private String fileName;
    private String extension;

    public long getRevision() {
        return revision;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public String getVersion() {
        return version;
    }

    public String getFileKey() {
        return fileKey;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getFileName() {
        return fileName;
    }

    public String getExtension() {
        return extension;
    }
}