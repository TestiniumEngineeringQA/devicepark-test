package org.devicepark.qa.model.request;

public class CreateAllocationRequest {

    private String manufacturer;
    private String model;
    private String platform;
    private String platformVersion;
    private String serial;
    private String removeApps;

    public CreateAllocationRequest(
            String manufacturer,
            String model,
            String platform,
            String platformVersion
    ) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.platform = platform;
        this.platformVersion = platformVersion;
    }

    public CreateAllocationRequest(String serial,  String removeApps) {
        this.serial = serial;
        this.removeApps = removeApps;
    }

    public CreateAllocationRequest(String serial) {
        this.serial = serial;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getPlatform() {
        return platform;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getSerial() {
        return serial;
    }

    public String getRemoveApps() {
        return removeApps;
    }
}