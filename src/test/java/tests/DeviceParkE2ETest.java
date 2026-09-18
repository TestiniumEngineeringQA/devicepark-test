package tests;

import action.Swipe;
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
import org.devicepark.qa.model.response.StartDeviceSessionResponse;
import org.devicepark.qa.model.response.StorageFileUploadResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.net.URI;
import java.time.Duration;

public class DeviceParkE2ETest extends BaseTest {

    private final Logger logger = LogManager.getLogger(DeviceParkE2ETest.class);
    private StorageFileUploadResponse storageFileUploadResponse;
    private StartDeviceSessionResponse sessionResponse;
    public static final String key = System.getenv("key");


    @Test
    public void appiumTestAndroid() {
        // IOS Capabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("testinium:key", key);

        desiredCapabilities.setCapability(
                "appium:appPackage",
                "com.deviceparktest.app"
        );

        desiredCapabilities.setCapability(
                "appium:appActivity",
                "com.deviceparktest.app.MainActivity"
        );

        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");

        desiredCapabilities.setCapability("appium:fullReset", true);
        desiredCapabilities.setCapability("appium:noReset", false);

        desiredCapabilities.setCapability("appium:unicodeKeyboard", true);
        desiredCapabilities.setCapability("appium:resetKeyboard", true);
        desiredCapabilities.setCapability("appium:autoGrantPermissions", true);

        desiredCapabilities.setCapability("appium:skipDeviceInitialization", true);
        desiredCapabilities.setCapability("appium:skipServerInstallation", true);

        desiredCapabilities.setCapability("appium:language", "tr");
        desiredCapabilities.setCapability("appium:locale", "TR");

        // Appium Driver
        try {
            WebDriver driver = new AndroidDriver(new URI("http://hub.testinium.io/wd/hub").toURL(), desiredCapabilities);

            if (driver == null) {
                throw new IllegalStateException("Driver oluşturulamadı!");
            }
            logger.info("Appium Gateway'e bağlantı sağlandı.");
            for (int i = 0; i < 5; i++) {
                Swipe.swipeDown(driver);
                Swipe.swipeUp(driver);
                logger.info("Swipe down-up");
            }
            driver.quit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test(groups = {"smoke", "android"})
    public void smokeTestFlowAndroid() {

        StorageFileUploadRequest fileUploadRequest =
                new StorageFileUploadRequest("/Users/mehmet.tanlak/Documents/release-artifacts-testinium/DeviceParkTest.apk", ".apk");
        StorageFileUploadClient storageFileUploadClient = new StorageFileUploadClient();
        storageFileUploadResponse = storageFileUploadClient.fileUpload(fileUploadRequest, ACCESS_TOKEN, true);

        // Allocation
        CreateAllocationRequest allocation = new CreateAllocationRequest("R5CT31AB4FV");
        CreateAllocationClient allocationClient = new CreateAllocationClient();
        CreateAllocationResponse allocationResponse = allocationClient.createDeviceAllocation(allocation, ACCESS_TOKEN);

        // Start Session
        StartDeviceSessionRequest session = new StartDeviceSessionRequest(
                allocationResponse.getAllocationId(),
                1,
                "mehmet.tanlak@testinium.com",
                1,
                "QA Automation - V2 API",
                true
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
            for (int i = 0; i < 10; i++) {
                Swipe.swipeDown(driver);
                Swipe.swipeUp(driver);
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
    }

    @Test(groups = {"smoke", "android"})
    public void smokeTestFlowAndroidTimeout() {

        StorageFileUploadRequest fileUploadRequest =
                new StorageFileUploadRequest("/Users/mehmet.tanlak/Documents/release-artifacts-testinium/DeviceParkTest.apk", ".apk");
        StorageFileUploadClient storageFileUploadClient = new StorageFileUploadClient();
        storageFileUploadResponse = storageFileUploadClient.fileUpload(fileUploadRequest, ACCESS_TOKEN, true);

        // Allocation
        CreateAllocationRequest allocation = new CreateAllocationRequest("R5CT31AB4FV");
        CreateAllocationClient allocationClient = new CreateAllocationClient();
        CreateAllocationResponse allocationResponse = allocationClient.createDeviceAllocation(allocation, ACCESS_TOKEN);

        // Start Session
        StartDeviceSessionRequest session = new StartDeviceSessionRequest(
                allocationResponse.getAllocationId(),
                1,
                "mehmet.tanlak@testinium.com",
                1,
                "QA Automation - V2 API",
                true
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
            for (int i = 0; i < 10; i++) {
                Swipe.swipeDown(driver);
                Swipe.swipeUp(driver);
                logger.info("Swipe down-up");
            }
            logger.info("30 seconds will be waiting...");
            Thread.sleep(30000);
            logger.info("The automation waited for 30 seconds....");
            Swipe.swipeDown(driver);
            Swipe.swipeUp(driver);
            driver.quit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Delete File
        /*
        StorageDeleteFileClient storageDeleteFileClient = new StorageDeleteFileClient();
        storageDeleteFileClient.deleteFile(storageFileUploadResponse.getFileKey(), ACCESS_TOKEN);
         */
    }

    @Test(groups = {"smoke", "ios"})
    public void smokeTestFlowIOS() {

        /*
        StorageFileUploadRequest fileUploadRequest =
                new StorageFileUploadRequest("/Users/mehmet.tanlak/Documents/release-artifacts-testinium/3.2.17_1743_-b5dcd671-2771701910726562778-9cb0cef6-9358686d-36fbbcdf-f4f7885d-a8f2dd38-15812756721719432845.ipa", ".ipa");
        StorageFileUploadClient storageFileUploadClient = new StorageFileUploadClient();
        storageFileUploadResponse = storageFileUploadClient.fileUpload(fileUploadRequest, ACCESS_TOKEN, true);
         */

        // Allocation
        CreateAllocationRequest allocation = new CreateAllocationRequest("00008101-001364541AE1001E");
        CreateAllocationClient allocationClient = new CreateAllocationClient();
        CreateAllocationResponse allocationResponse = allocationClient.createDeviceAllocation(allocation, ACCESS_TOKEN);

        // Start Session
        StartDeviceSessionRequest session = new StartDeviceSessionRequest(
                allocationResponse.getAllocationId(),
                1,
                "mehmet.tanlak@testinium.com",
                1,
                "QA Automation - V2 API",
                true
        );

        StartDeviceSessionClient client = new StartDeviceSessionClient();
        sessionResponse = client.createDeviceSession(session, ACCESS_TOKEN);

        // IOS Capabilities
        IOSCapabilities iosCapabilities = new IOSCapabilities("ed732a0a-0404-413d-81de-a7baa6b0f915", sessionResponse.getSessionId());
        DesiredCapabilities desiredCapabilities = iosCapabilities.buildCapabilities();

        // Appium Driver
        try {
            WebDriver driver = new IOSDriver(new URI("https://dev-devicepark-appium-gw-service.testinium.io/wd/hub").toURL(), desiredCapabilities);

            if (driver == null) {
                throw new IllegalStateException("Driver oluşturulamadı!");
            }
            logger.info("Appium Gateway'e bağlantı sağlandı.");
            for (int i = 0; i < 5; i++) {
                Swipe.swipeDown(driver);
                Swipe.swipeUp(driver);
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

    }

    @AfterMethod(alwaysRun = true)
    public void afterTestProcess() {
        // Close Session
        CloseDeviceSessionRequest closeSession = new CloseDeviceSessionRequest(sessionResponse.getSessionId());
        CloseDeviceSessionClient closeDeviceSessionClient = new CloseDeviceSessionClient();
        closeDeviceSessionClient.deleteDeviceSession(closeSession.getSessionId(), ACCESS_TOKEN);
    }
}