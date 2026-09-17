package org.devicepark.qa.client;

import io.restassured.response.Response;
import org.devicepark.qa.model.request.CreateAllocationRequest;
import org.devicepark.qa.model.response.CreateAllocationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class CreateAllocationClient {

    private final Logger logger = LogManager.getLogger(CreateAllocationClient.class);

    public CreateAllocationResponse createDeviceAllocation(CreateAllocationRequest allocationRequest, String token) {

        Response response =
                given()
                        .log().method()
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .body(allocationRequest)
                        .when()
                        .post("/allocation/api/v2/public/allocations")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        CreateAllocationResponse allocationResponse = response.as(CreateAllocationResponse.class);
        logger.info("Device allocation created: {}", response.asPrettyString());
        return allocationResponse;
    }
}