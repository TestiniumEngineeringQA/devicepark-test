package org.devicepark.qa.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.devicepark.qa.model.request.StartDeviceSessionRequest;
import org.devicepark.qa.model.response.StartDeviceSessionResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class StartDeviceSessionClient {

    private final Logger logger = LogManager.getLogger(StartDeviceSessionClient.class);

    public StartDeviceSessionResponse createDeviceSession(StartDeviceSessionRequest session, String token) {
        logger.info("Creating device session for allocationId: {}", session.getAllocationId());
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(session)
                .when()
                .post("/session/api/v2/public/sessions")
                .then()
                .statusCode(200)
                .extract()
                .response();

        StartDeviceSessionResponse sessionResponse = response.as(StartDeviceSessionResponse.class);
        logger.info("Device session created: {}", response.asPrettyString());
        return sessionResponse;
    }
}