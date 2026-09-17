package org.devicepark.qa.model.response;

import java.time.OffsetDateTime;

public class GetAllocationResponse {

    private String allocationId;
    private String deviceSerial;
    private String requestId;
    private Integer position;
    private OffsetDateTime expiresAt;


    public String getAllocationId() {
        return allocationId;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public String getRequestId() {
        return requestId;
    }

    public Integer getPosition() {
        return position;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }
}
