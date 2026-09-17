package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class BaseTest {

    public static String ACCESS_TOKEN;

    @BeforeSuite(alwaysRun = true)
    public void setup() {
        RestAssured.baseURI = "https://dev-devicepark.testinium.io";

        // TOKEN
        Response response =
                given()
                        .log().method().auth().preemptive()
                        .basic("Mehmet_AppiumGW_Client", "$2a$10$5.9qc8TnwQaGJm935LdpfO3sN/dy3t1WORi07JFZM4hOsTJv9sejy")
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("grant_type", "client_credentials")
                        .formParam("scope", "openid")
                        .when()
                        .post("/uaa/oauth2/token")
                        .then().log().body()
                        .statusCode(200)
                        .body("access_token", notNullValue())
                        .extract()
                        .response();

        ACCESS_TOKEN = response.jsonPath().getString("access_token");
    }

    @BeforeMethod
    public void beforeTest(ITestResult result) {
        System.out.println(
                "🚀 START | Test: " + result.getMethod().getMethodName() +
                        " | Thread: " + Thread.currentThread().getId()
        );
    }

    @AfterMethod
    public void afterTest(ITestResult result) {
        System.out.println(
                "✅ END   | Test: " + result.getMethod().getMethodName() +
                        " | Thread: " + Thread.currentThread().getId() +
                        " | Status: " + (result.isSuccess() ? "PASS" : "FAIL")
        );
    }
}