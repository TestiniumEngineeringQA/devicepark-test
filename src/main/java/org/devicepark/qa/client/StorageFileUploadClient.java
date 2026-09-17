package org.devicepark.qa.client;

import io.restassured.response.Response;
import org.devicepark.qa.model.request.StorageFileUploadRequest;
import org.devicepark.qa.model.response.StorageFileUploadResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class StorageFileUploadClient {

    private final Logger logger = LogManager.getLogger(StorageFileUploadClient.class);

    public StorageFileUploadResponse fileUpload(StorageFileUploadRequest upload, String token, boolean multipart) {

        File file = new File(upload.getFilePath());

        if (!file.exists()) {
            throw new RuntimeException("File does not exist: " + upload.getFilePath());
        }

        logger.info("Uploading : {}", file.getAbsolutePath());
        logger.info("Is Directory : {}", file.isDirectory());

        Response response;

        if (multipart) {

            response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("file-name", "Test_Automation" + upload.getFileType())
                    .header("version", "header")
                    .queryParam("version", "Query")
                    .multiPart("file", file)
                    .when()
                    .post("/storage/api/v1/public/applications")
                    .then()
                    .extract()
                    .response();

        } else {

            response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("file-name", "Test_Automation" + upload.getFileType())
                    .header("version", "header")
                    .contentType("application/octet-stream")
                    .queryParam("version", "Query")
                    .body(file)
                    .when()
                    .post("/storage/api/v1/public/applications")
                    .then()
                    .extract()
                    .response();
        }

        logger.info("Status Code : {}", response.statusCode());
        logger.info("Response : {}", response.asPrettyString());

        if (response.statusCode() >= 400) {
            throw new RuntimeException(
                    "Upload failed. Status Code: "
                            + response.statusCode()
                            + " Response: "
                            + response.asString());
        }

        return response.as(StorageFileUploadResponse.class);
    }

}