package org.devicepark.qa.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class AndroidCapabilities {

    private String fileKey;
    private String sessionId;

    public AndroidCapabilities(String fileKey, String sessionId) {
        this.fileKey = fileKey;
        this.sessionId = sessionId;
    }

    public DesiredCapabilities buildCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:app", "dp://" + fileKey);
        capabilities.setCapability("devicePark:sessionId", sessionId);
        return capabilities;
    }
}