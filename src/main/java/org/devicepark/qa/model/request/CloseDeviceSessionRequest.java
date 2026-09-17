package org.devicepark.qa.model.request;

public class CloseDeviceSessionRequest {

    private final String sessionId;

    public CloseDeviceSessionRequest(String sessionId) {
        this.sessionId = sessionId;

    }

    public String getSessionId() {
        return sessionId;
    }
}
