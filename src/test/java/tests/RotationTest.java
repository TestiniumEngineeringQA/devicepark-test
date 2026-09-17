package tests;

import action.Swipe;
import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.devicepark.qa.capabilities.AndroidCapabilities;
import org.devicepark.qa.capabilities.IOSCapabilities;
import org.devicepark.qa.client.CloseDeviceSessionClient;
import org.devicepark.qa.client.CreateAllocationClient;
import org.devicepark.qa.client.GetAllocationClient;
import org.devicepark.qa.client.StartDeviceSessionClient;
import org.devicepark.qa.model.request.CloseDeviceSessionRequest;
import org.devicepark.qa.model.request.CreateAllocationRequest;
import org.devicepark.qa.model.request.StartDeviceSessionRequest;
import org.devicepark.qa.model.response.CreateAllocationResponse;
import org.devicepark.qa.model.response.GetAllocationResponse;
import org.devicepark.qa.model.response.StartDeviceSessionResponse;
import org.devicepark.qa.model.response.StorageFileUploadResponse;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.URI;

public class RotationTest extends BaseTest {

    private final Logger logger = LogManager.getLogger(RotationTest.class);
    private StorageFileUploadResponse storageFileUploadResponse;
    private StartDeviceSessionResponse sessionResponse;

    @Test(groups = {"rotation"})
    public void smokeTestFlowIOS() {

        // Allocation
        CreateAllocationRequest allocation = new CreateAllocationRequest("0b9c4e297b29ee53c5589ebd5242f302c0f66f26");
        CreateAllocationClient allocationClient = new CreateAllocationClient();
        CreateAllocationResponse allocationResponse = allocationClient.createDeviceAllocation(allocation, ACCESS_TOKEN);

        /*
        // Get Allocation Request Id
        GetAllocationClient getAllocationClient = new GetAllocationClient();
        GetAllocationResponse getAllocationResponse = getAllocationClient.deviceAllocation(allocationResponse.getRequestId(), ACCESS_TOKEN);

         */

        // Start Session
        StartDeviceSessionRequest session = new StartDeviceSessionRequest(
                allocationResponse.getAllocationId(),
                1,
                "mehmet.tanlak@testinium.com",
                1,
                "QA Automation - V2 API",
                true, "3.5"
        );

        StartDeviceSessionClient client = new StartDeviceSessionClient();
        sessionResponse = client.createDeviceSession(session, ACCESS_TOKEN);

        // IOS Capabilities
        IOSCapabilities iosCapabilities = new IOSCapabilities("", sessionResponse.getSessionId());
        DesiredCapabilities desiredCapabilities = iosCapabilities.buildCapabilities();

        // Appium Driver
        try {
            WebDriver driver = new IOSDriver(new URI("https://dev-devicepark-appium-gw-service.testinium.io/wd/hub").toURL(), desiredCapabilities);

            if (driver == null) {
                throw new IllegalStateException("Driver oluşturulamadı!");
            }
            logger.info("Appium Gateway'e bağlantı sağlandı.");
            System.out.println("First Rotation : "+((IOSDriver) driver).getOrientation());
            Thread.sleep(10000);
            ((IOSDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
            System.out.println("Second Rotation : "+((IOSDriver) driver).getOrientation());
            Thread.sleep(20000);
            driver.quit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Close Session
        CloseDeviceSessionRequest closeSession = new CloseDeviceSessionRequest(sessionResponse.getSessionId());
        CloseDeviceSessionClient closeDeviceSessionClient = new CloseDeviceSessionClient();
        closeDeviceSessionClient.deleteDeviceSession(closeSession.getSessionId(), ACCESS_TOKEN);
    }

    @Test(groups = {"smoke", "android"})
    public void smokeTestFlowAndroid() {
        // Allocation
        CreateAllocationRequest allocation = new CreateAllocationRequest("node-10.0.15.23-pixel_6-android_34-1-emulator-5554");
        CreateAllocationClient allocationClient = new CreateAllocationClient();
        CreateAllocationResponse allocationResponse = allocationClient.createDeviceAllocation(allocation, ACCESS_TOKEN);

        /*
        // Get Allocation Request Id
        GetAllocationClient getAllocationClient = new GetAllocationClient();
        GetAllocationResponse getAllocationResponse = getAllocationClient.deviceAllocation(allocationResponse.getRequestId(), ACCESS_TOKEN);

         */

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
        AndroidCapabilities androidCapabilities = new AndroidCapabilities("storageFileUploadResponse.getFileKey()", sessionResponse.getSessionId());
        DesiredCapabilities desiredCapabilities = androidCapabilities.buildCapabilities();

        try {
            WebDriver driver = new AndroidDriver(new URI("https://dev-devicepark-appium-gw-service.testinium.io/wd/hub").toURL(), desiredCapabilities);
            if (driver == null) {
                throw new IllegalStateException("Driver oluşturulamadı!");
            }
            logger.info("Appium Gateway'e bağlantı sağlandı.");
            System.out.println("First Rotation : "+((AndroidDriver) driver).getOrientation());
            Thread.sleep(10000);
            ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
            System.out.println("Second Rotation : "+((AndroidDriver) driver).getOrientation());
            Thread.sleep(10000);
            driver.quit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Close Session
        CloseDeviceSessionRequest closeSession = new CloseDeviceSessionRequest(sessionResponse.getSessionId());
        CloseDeviceSessionClient closeDeviceSessionClient = new CloseDeviceSessionClient();
        closeDeviceSessionClient.deleteDeviceSession(closeSession.getSessionId(), ACCESS_TOKEN);
    }

}