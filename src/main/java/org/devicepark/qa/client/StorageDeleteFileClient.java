package org.devicepark.qa.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class StorageDeleteFileClient {

    private final Logger logger = LogManager.getLogger(StorageDeleteFileClient.class);

    public void deleteFile(String fileKey, String accessToken) {

        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete("/storage/api/v1/public/applications/{fileKey}", fileKey)
                .then()
                .statusCode(204)
                .log().status()
                .extract()
                .response();
        logger.info("File successfully deleted. fileKey={}", fileKey);
    }
}