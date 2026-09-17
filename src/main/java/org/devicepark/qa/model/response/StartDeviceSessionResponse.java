package org.devicepark.qa.model.response;

public class StartDeviceSessionResponse {
    private Long id;
    private String state;
    private String client;
    private String sessionId;
    private String allocationId;
    private String appiumVersion;


    private String startDate;
    private String endDate;
    private String latestInteractionTime;

    private Long userId;
    private String userEmail;
    private Long companyId;
    private String companyName;

    private String deviceSerial;
    private String deviceName;
    private String deviceModel;
    private String deviceManufacturer;
    private String devicePlatform;
    private String deviceVersion;

    private String cliServiceUrl;
    private Boolean videoRecording;
    private String videoRecordUrl;

    private Integer deviceWdaPort;
    private String deviceMjpegPort;
    private String deviceAdbPort;

    private String hubIp;
    private String appiumPort;

    private String createdAt;
    private String updatedAt;

    private String closeFailureReason;
    private Integer closeAttemptCount;

    private String dataAccessEndDate;
    private String dataRetentionEndDate;

    private String customVideoRecordingPath;

    // Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(String allocationId) {
        this.allocationId = allocationId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLatestInteractionTime() {
        return latestInteractionTime;
    }

    public void setLatestInteractionTime(String latestInteractionTime) {
        this.latestInteractionTime = latestInteractionTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public void setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public String getCliServiceUrl() {
        return cliServiceUrl;
    }

    public void setCliServiceUrl(String cliServiceUrl) {
        this.cliServiceUrl = cliServiceUrl;
    }

    public Boolean getVideoRecording() {
        return videoRecording;
    }

    public void setVideoRecording(Boolean videoRecording) {
        this.videoRecording = videoRecording;
    }

    public String getVideoRecordUrl() {
        return videoRecordUrl;
    }

    public void setVideoRecordUrl(String videoRecordUrl) {
        this.videoRecordUrl = videoRecordUrl;
    }

    public Integer getDeviceWdaPort() {
        return deviceWdaPort;
    }

    public void setDeviceWdaPort(Integer deviceWdaPort) {
        this.deviceWdaPort = deviceWdaPort;
    }

    public String getDeviceMjpegPort() {
        return deviceMjpegPort;
    }

    public void setDeviceMjpegPort(String deviceMjpegPort) {
        this.deviceMjpegPort = deviceMjpegPort;
    }

    public String getDeviceAdbPort() {
        return deviceAdbPort;
    }

    public void setDeviceAdbPort(String deviceAdbPort) {
        this.deviceAdbPort = deviceAdbPort;
    }

    public String getHubIp() {
        return hubIp;
    }

    public void setHubIp(String hubIp) {
        this.hubIp = hubIp;
    }

    public String getAppiumPort() {
        return appiumPort;
    }

    public void setAppiumPort(String appiumPort) {
        this.appiumPort = appiumPort;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCloseFailureReason() {
        return closeFailureReason;
    }

    public void setCloseFailureReason(String closeFailureReason) {
        this.closeFailureReason = closeFailureReason;
    }

    public Integer getCloseAttemptCount() {
        return closeAttemptCount;
    }

    public void setCloseAttemptCount(Integer closeAttemptCount) {
        this.closeAttemptCount = closeAttemptCount;
    }

    public String getDataAccessEndDate() {
        return dataAccessEndDate;
    }

    public void setDataAccessEndDate(String dataAccessEndDate) {
        this.dataAccessEndDate = dataAccessEndDate;
    }

    public String getDataRetentionEndDate() {
        return dataRetentionEndDate;
    }

    public void setDataRetentionEndDate(String dataRetentionEndDate) {
        this.dataRetentionEndDate = dataRetentionEndDate;
    }

    public String getCustomVideoRecordingPath() {
        return customVideoRecordingPath;
    }

    public void setCustomVideoRecordingPath(String customVideoRecordingPath) {
        this.customVideoRecordingPath = customVideoRecordingPath;
    }

    public String getAppiumVersion() {
        return appiumVersion;
    }

    public void setAppiumVersion(String appiumVersion) {
        this.appiumVersion = appiumVersion;
    }

}
