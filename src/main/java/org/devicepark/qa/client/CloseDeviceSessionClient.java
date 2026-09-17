package org.devicepark.qa.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class CloseDeviceSessionClient {

    private final Logger logger = LogManager.getLogger(CloseDeviceSessionClient.class);


    public void deleteDeviceSession(String sessionId, String token) {

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .pathParam("sessionId", sessionId)
                .when()
                .delete("/session/api/v2/public/sessions/{sessionId}")
                .then()
                .statusCode(204)
                .extract()
                .response();
        logger.info("Session {} successfully closed", sessionId);
    }
}