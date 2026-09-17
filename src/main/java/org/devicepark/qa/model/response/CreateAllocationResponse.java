package org.devicepark.qa.model.response;

public class CreateAllocationResponse {

    private String allocationId;
    private String deviceSerial;
    private String requestId;

    private String position;
    private String expiresAt;
    private String removeApps;

    public String getAllocationId() {
        return allocationId;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getPosition() {
        return position;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public String getRemoveApps() {
        return removeApps;
    }
}