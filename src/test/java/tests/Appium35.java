package tests;

import action.Swipe;
import action.SwipeLast;
import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.devicepark.qa.capabilities.AndroidCapabilities;
import org.devicepark.qa.capabilities.IOSCapabilities;
import org.devicepark.qa.client.*;
import org.devicepark.qa.model.request.CloseDeviceSessionRequest;
import org.devicepark.qa.model.request.CreateAllocationRequest;
import org.devicepark.qa.model.request.StartDeviceSessionRequest;
import org.devicepark.qa.model.request.StorageFileUploadRequest;
import org.devicepark.qa.model.response.CreateAllocationResponse;
import org.devicepark.qa.model.response.GetAllocationResponse;
import org.devicepark.qa.model.response.StartDeviceSessionResponse;
import org.devicepark.qa.model.response.StorageFileUploadResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.URI;

public class Appium35 extends BaseTest {

    private final Logger logger = LogManager.getLogger(Appium35.class);
    private StorageFileUploadResponse storageFileUploadResponse;
    private StartDeviceSessionResponse sessionResponse;

    @Test(groups = {"smoke", "android"})
    public void smokeTestFlowAndroid() {

        StorageFileUploadRequest fileUploadRequest =
                new StorageFileUploadRequest("/Users/mehmet.tanlak/Documents/release-artifacts/DeviceParkTest.apk", ".apk");
        StorageFileUploadClient storageFileUploadClient = new StorageFileUploadClient();
        storageFileUploadResponse = storageFileUploadClient.fileUpload(fileUploadRequest, ACCESS_TOKEN, false);

        // Allocation
        CreateAllocationRequest allocation = new CreateAllocationRequest("R58N85AEVXX");
        CreateAllocationClient allocationClient = new CreateAllocationClient();
        CreateAllocationResponse allocationResponse = allocationClient.createDeviceAllocation(allocation, ACCESS_TOKEN);

        // Get Allocation Request Id
        GetAllocationClient getAllocationClient = new GetAllocationClient();
        GetAllocationResponse getAllocationResponse = getAllocationClient.deviceAllocation(allocationResponse.getAllocationId(), ACCESS_TOKEN);

        // Start Session

        StartDeviceSessionRequest session = new StartDeviceSessionRequest(
                getAllocationResponse.getAllocationId(),
                1,
                "mehmet.tanlak@testinium.com",
                1,
                "QA Automation - V2 API",
                false
        );
        StartDeviceSessionClient client = new StartDeviceSessionClient();
        sessionResponse = client.createDeviceSession(session, ACCESS_TOKEN);

        // Android Capabilities
        AndroidCapabilities androidCapabilities = new AndroidCapabilities(storageFileUploadResponse.getFileKey(), sessionResponse.getSessionId());
        DesiredCapabilities desiredCapabilities = androidCapabilities.buildCapabilities();

        try {
            WebDriver driver = new AndroidDriver(new URI("https://dev-devicepark-appium-gw-service.testinium.io/wd/hub").toURL(), desiredCapabilities);
            if (driver == null) {
                throw new IllegalStateException("Driver oluşturulamadı!");
            }
            logger.info("Appium Gateway'e bağlantı sağlandı.");
            Thread.sleep(10000);
            for (int i = 0; i < 5; i++) {
                Swipe.swipeLeft(driver);
                Swipe.swipeRight(driver);
                logger.info("Swipe right-left");
            }
            driver.quit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Delete File
        /*
        StorageDeleteFileClient storageDeleteFileClient = new StorageDeleteFileClient();
        storageDeleteFileClient.deleteFile(storageFileUploadResponse.getFileKey(), ACCESS_TOKEN);
         */

        // Close Session
        CloseDeviceSessionRequest closeSession = new CloseDeviceSessionRequest(sessionResponse.getSessionId());
        CloseDeviceSessionClient closeDeviceSessionClient = new CloseDeviceSessionClient();
        closeDeviceSessionClient.deleteDeviceSession(closeSession.getSessionId(), ACCESS_TOKEN);
    }

    @Test(groups = {"smoke", "ios"})
    public void smokeTestFlowIOS() {

        StorageFileUploadRequest fileUploadRequest =
                new StorageFileUploadRequest("/Users/mehmet.tanlak/Documents/release-artifacts/DeviceParkTestModern-unsigned.ipa", ".ipa");
        StorageFileUploadClient storageFileUploadClient = new StorageFileUploadClient();
        storageFileUploadResponse = storageFileUploadClient.fileUpload(fileUploadRequest, ACCESS_TOKEN, false);


        // Allocation
        CreateAllocationRequest allocation = new CreateAllocationRequest("00008110-00024DAA2E50401E");
        CreateAllocationClient allocationClient = new CreateAllocationClient();
        CreateAllocationResponse allocationResponse = allocationClient.createDeviceAllocation(allocation, ACCESS_TOKEN);

        // Get Allocation Request Id
        GetAllocationClient getAllocationClient = new GetAllocationClient();
        GetAllocationResponse getAllocationResponse = getAllocationClient.deviceAllocation(allocationResponse.getAllocationId(), ACCESS_TOKEN);

        // Start Session
        StartDeviceSessionRequest session = new StartDeviceSessionRequest(
                getAllocationResponse.getAllocationId(),
                1,
                "mehmet.tanlak@testinium.com",
                1,
                "QA Automation - V2 API",
                false, "3.5"
        );

        StartDeviceSessionClient client = new StartDeviceSessionClient();
        sessionResponse = client.createDeviceSession(session, ACCESS_TOKEN);

        // IOS Capabilities
        IOSCapabilities iosCapabilities = new IOSCapabilities(storageFileUploadResponse.getFileKey(), sessionResponse.getSessionId());
        DesiredCapabilities desiredCapabilities = iosCapabilities.buildCapabilities();

        // Appium Driver
        try {
            WebDriver driver = new IOSDriver(new URI("https://dev-devicepark-appium-gw-service.testinium.io/wd/hub").toURL(), desiredCapabilities);

            if (driver == null) {
                throw new IllegalStateException("Driver oluşturulamadı!");
            }
            logger.info("Appium Gateway'e bağlantı sağlandı.");
            Thread.sleep(10000);
            for (int i = 0; i < 5; i++) {
                SwipeLast.swipeLeftLast(driver);
                SwipeLast.swipeRightLast(driver);
                logger.info("Swipe down-up");
            }
            driver.quit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Delete File
        /*
        StorageDeleteFileClient storageDeleteFileClient = new StorageDeleteFileClient();
        storageDeleteFileClient.deleteFile(storageFileUploadResponse.getFileKey(), ACCESS_TOKEN);
         */

        // Close Session
        CloseDeviceSessionRequest closeSession = new CloseDeviceSessionRequest(sessionResponse.getSessionId());
        CloseDeviceSessionClient closeDeviceSessionClient = new CloseDeviceSessionClient();
        closeDeviceSessionClient.deleteDeviceSession(closeSession.getSessionId(), ACCESS_TOKEN);
    }
}


/*
            WebDriverWait wait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(20)
            );
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@content-desc='login']")));

 */


/*
            driver.findElement(By.id("appium-devicepark-appium-gw-service")).click();
 */


/*
            for (int i = 0; i < 60; i++) {
                Swipe.swipeRight(driver);
                Thread.sleep(500);
                Swipe.swipeLeft(driver);
                Thread.sleep(500);
            }
 */


/*

            //Thread.sleep(3000);
            //driver.findElement(By.id("com.pozitron.pegasus.externalRelease:id/fragmentSemiForcedLoginButtonGuestContinue")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[contains(@resource-id, 'com.pozitron.pegasus.externalRelease:id/navigation_bar_item_small_label_view') and contains(@text, 'Check-in')]")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[contains(@resource-id, 'com.pozitron.pegasus.externalRelease:id/pnr_search_input_view_surname')]")).click();
            driver.findElement(By.xpath("//*[contains(@resource-id, 'com.pozitron.pegasus.externalRelease:id/pnr_search_input_view_surname')]")).sendKeys("AA333");
            Thread.sleep(3000);
            driver.findElement(By.xpath("com.pozitron.pegasus.externalRelease:id/pnr_search_button_search")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[contains(@resource-id, 'com.pozitron.pegasus.externalRelease:id/navigation_bar_item_small_label_view') and contains(@text, 'Ana Sayfa')]")).click();
            Thread.sleep(3000);
            Swipe.swipeUp(driver);
            Thread.sleep(3000);
 */