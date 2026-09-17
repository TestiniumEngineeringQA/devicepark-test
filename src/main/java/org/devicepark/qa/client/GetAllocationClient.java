package org.devicepark.qa.client;

import io.restassured.response.Response;
import org.devicepark.qa.model.response.GetAllocationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class GetAllocationClient {

    private final Logger logger = LogManager.getLogger(GetAllocationClient.class);

    public GetAllocationResponse deviceAllocation(String requestId, String token) {
        Response response =
                given()
                        .log().method()
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .pathParam("requestId", requestId)
                        .when()
                        .get("/allocation/api/device-allocations-requests/{requestId}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();


        GetAllocationResponse sessionResponse = response.as(GetAllocationResponse.class);
        logger.info("Get Allocation Response : {}", response.asPrettyString());
        return sessionResponse;
    }
}