package org.devicepark.qa.model.request;

public class StartDeviceSessionRequest {

    private final String allocationId;
    private final int userId;
    private final String userEmail;
    private final int companyId;
    private final String companyName;
    private final boolean videoRecording;
    private String appiumVersion;

    public StartDeviceSessionRequest(String allocationId, int userId, String userEmail,
                                     int companyId, String companyName, boolean videoRecording, String appiumVersion) {
        this.allocationId = allocationId;
        this.userId = userId;
        this.userEmail = userEmail;
        this.companyId = companyId;
        this.companyName = companyName;
        this.videoRecording = videoRecording;
        this.appiumVersion = appiumVersion;
    }

    public StartDeviceSessionRequest(String allocationId, int userId, String userEmail,
                                     int companyId, String companyName, boolean videoRecording) {
        this.allocationId = allocationId;
        this.userId = userId;
        this.userEmail = userEmail;
        this.companyId = companyId;
        this.companyName = companyName;
        this.videoRecording = videoRecording;
    }


    public String getAllocationId() {
        return allocationId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getAppiumVersion() {
        return appiumVersion;
    }

    public void setAppiumVersion(String appiumVersion) {
        this.appiumVersion = appiumVersion;
    }

    public String getCompanyName() {
        return companyName;
    }

    public boolean isVideoRecording() {
        return videoRecording;
    }
}