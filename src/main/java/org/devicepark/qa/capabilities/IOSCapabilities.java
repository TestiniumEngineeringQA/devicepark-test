package org.devicepark.qa.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class IOSCapabilities {

    private String fileKey;
    private String sessionId;

    public IOSCapabilities(String fileKey, String sessionId) {
        this.fileKey = fileKey;
        this.sessionId = sessionId;
    }

    public DesiredCapabilities buildCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:app", "dp://" + fileKey);
        capabilities.setCapability("devicePark:sessionId", sessionId);
        //capabilities.setCapability("appium:iosInstallPause", 3000);
        return capabilities;
    }
}