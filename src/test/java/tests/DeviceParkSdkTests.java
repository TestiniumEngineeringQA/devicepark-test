package tests;

import io.testinium.devicepark.DeviceParkApiClient;
import io.testinium.devicepark.authentication.credentials.Credentials;
import io.testinium.devicepark.model.allocation.Allocation;
import io.testinium.devicepark.model.allocation.AllocationSearchRequest;
import io.testinium.devicepark.model.allocation.DeviceAllocationRequest;
import io.testinium.devicepark.model.allocation.RemoveAppSelection;
import io.testinium.devicepark.model.common.PageDto;
import io.testinium.devicepark.model.common.SearchOperation;
import io.testinium.devicepark.model.common.SortDirection;
import io.testinium.devicepark.model.devices.Device;
import io.testinium.devicepark.model.devices.DeviceFilter;
import io.testinium.devicepark.model.devices.DeviceFilterRequest;
import io.testinium.devicepark.model.devices.ListDevicesRequest;
import io.testinium.devicepark.model.pools.ListPoolsRequest;
import io.testinium.devicepark.model.pools.Pool;
import io.testinium.devicepark.model.sessions.DeviceStartSessionRequest;
import io.testinium.devicepark.model.sessions.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Collections;

import io.testinium.devicepark.model.sessions.DeviceSessionFilterRequest;
import io.testinium.devicepark.model.sessions.DeviceSessionRequest;
import io.testinium.devicepark.model.sessions.SessionFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.testng.AssertJUnit.assertNotNull;


public class DeviceParkSdkTests {

    private static final Logger log = LoggerFactory.getLogger(DeviceParkSdkTests.class);
    public final String DEVICEPARK_CLIENT_ID = "Mehmet_AppiumGW_Client";
    public final String DEVICEPARK_CLIENT_SECRET = "$2a$10$5.9qc8TnwQaGJm935LdpfO3sN/dy3t1WORi07JFZM4hOsTJv9sejy";

    @Test
    public void getDeviceFilter(){
        //Auth
        DeviceParkApiClient client = DeviceParkApiClient.builder()
                .url("https://dev-devicepark.testinium.io")
                .credentials(Credentials.of(DEVICEPARK_CLIENT_ID, DEVICEPARK_CLIENT_SECRET))
                .build();

        DeviceAllocationRequest allocationRequest = new DeviceAllocationRequest();

        /*
        allocationRequest.setManufacturer("samsung");
        allocationRequest.setModel("SM-G990E");
        allocationRequest.setPlatform("android");
        allocationRequest.setPlatformVersion("14");
         */

        allocationRequest.setManufacturer("Apple");
        allocationRequest.setModel("MHNG3");
        allocationRequest.setPlatform("iOS");
        allocationRequest.setPlatformVersion("18.5");

        List<DeviceFilterRequest> filters = new ArrayList<>();
        filters.add(DeviceFilterRequest.of(DeviceFilter.MANUFACTURER, allocationRequest.getManufacturer(), SearchOperation.EQUAL));
        filters.add(DeviceFilterRequest.of(DeviceFilter.MODEL_NAME, allocationRequest.getModel(), SearchOperation.EQUAL));
        filters.add(DeviceFilterRequest.of(DeviceFilter.PLATFORM, allocationRequest.getPlatform(), SearchOperation.EQUAL));
        filters.add(DeviceFilterRequest.of(DeviceFilter.PLATFORM_VERSION, allocationRequest.getPlatformVersion(), SearchOperation.EQUAL));
        filters.add(DeviceFilterRequest.of(DeviceFilter.STATE, "HEALTHY", SearchOperation.EQUAL));

        PageDto<Device> devices = client.devices().list(
                ListDevicesRequest.builder()
                        .page(0)
                        .size(20)
                        .filters(filters)
                        .build()
        );

        Device device = devices.data().get(0);
        assertNotNull(device.serial());
        System.out.println("Selected first device:");
        System.out.printf(
                "  id=%s | serial=%s | %s | %s %s | state=%s%n",
                device.id(),
                device.serial(),
                device.marketName(),
                device.platform(),
                device.platformVersion(),
                device.state()
        );

    }

    @Test
    public void e2eFlow(){
        //Auth
        DeviceParkApiClient client = DeviceParkApiClient.builder()
                .url("https://dev-devicepark.testinium.io")
                .credentials(Credentials.of(DEVICEPARK_CLIENT_ID, DEVICEPARK_CLIENT_SECRET))
                .build();

        log.info("Device Park API client token created successfully");

        Allocation allocation = client.allocations().create(
                DeviceAllocationRequest.builder()
                        .serial("0b9c4e297b29ee53c5589ebd5242f302c0f66f26")
                        .removeApps(RemoveAppSelection.NO_REMOVE)
                        .build());

        if (allocation.allocationId() == null) {
            throw new IllegalStateException("Allocation id is missing");
        }
        log.info(allocation.toString());

        //client.allocations().list(AllocationSearchRequest.builder().filters())
    }

    @Test
    public void sdkSampleTest() throws  Exception {

        //Auth
        DeviceParkApiClient client = DeviceParkApiClient.builder()
                .url("https://dev-devicepark.testinium.io")
                .credentials(Credentials.of(DEVICEPARK_CLIENT_ID, DEVICEPARK_CLIENT_SECRET))
                .build();

        /* Upload Application */
        /*
        InputStream fileStream = Files.newInputStream(Path.of("/Users/mehmet.tanlak/Documents/release-artifacts/DeviceParkTest.apk"));
        Application uploaded = client.applications().upload(fileStream, "DeviceParkTest.apk", "1.0.0");
        log.info("Uploaded Application File: {} ", uploaded.fileKey());
        log.info("Uploaded Application Download URL: {} ", uploaded.downloadUrl());
        log.info("Uploaded Application File PATH: {} ", uploaded.filePath());
         */

        //Cihaz listeleme
        PageDto<Device> devices = client.devices().list(
                ListDevicesRequest.builder().page(0).size(20).build());

        //client.devices().get("00008020-000C19993602002E");

        Device selectedDevice = devices.data().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No device is available"));

        log.info(selectedDevice.serial());


        //Allocation başlatma
        Allocation allocation = client.allocations().create(
                DeviceAllocationRequest.builder()
                        .devicePoolId("0d28cba0-8fe4-46f5-bce6-e74b6901b103")
                        .priority(1)
                        .build());


        if (allocation.allocationId() == null) {
            throw new IllegalStateException("Allocation id is missing");
        }

        //Session start
        Session session = client.sessions().start(DeviceStartSessionRequest.builder()
                .allocationId(allocation.allocationId())
                .userId(1L)
                .companyId(1L)
                .companyName("Mehmet")
                .userEmail("mehmet.tanlak@testinium.com").build());
        log.info("Session Start: {} ", session.sessionId());

        Thread.sleep(5000);
        /*
        AndroidCapabilities androidCapabilities = new AndroidCapabilities(uploaded.fileKey(), session.sessionId());
        DesiredCapabilities desiredCapabilities = androidCapabilities.buildCapabilities();

        try {
            WebDriver driver = new AndroidDriver(new URI("https://dev-devicepark-appium-gw-service.testinium.io/wd/hub").toURL(), desiredCapabilities);
            if (driver == null) {
                throw new IllegalStateException("Driver oluşturulamadı!");
            }
            log.info("Appium Gateway'e bağlantı sağlandı.");
            Thread.sleep(5000);
            for (int i = 0; i < 5; i++) {
                Swipe.swipeLeft(driver);
                Swipe.swipeRight(driver);
                log.info("Swipe down-up");
            }
            driver.quit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
         */

        //Session stop
        client.sessions().stop(session.sessionId());

        //allocation delete
        client.allocations().delete(allocation.allocationId());

    }

    @Test
    void listByDefaultPool() {
        DeviceParkApiClient client = DeviceParkApiClient.builder()
                .url("https://dev-devicepark.testinium.io")
                .credentials(Credentials.of(DEVICEPARK_CLIENT_ID, DEVICEPARK_CLIENT_SECRET))
                .build();

        PageDto<Pool> poolsAll = client.pools().list(ListPoolsRequest.builder()
                .page(0)
                .size(20)
                .direction(SortDirection.DESC)
                .sortBy("ID").build());


        for (Pool pool : poolsAll.data()) {
            log.info("Pool: {} ", pool.id()+" isDefault: "+ pool.isDefault());
        }

        /* List Default Pools */
        /*
        PageDto<Pool> poolDefault = client.pools().listByDefaultPool(ListPoolsRequest.builder()
                .page(0)
                .size(20)
                .direction(SortDirection.DESC)
                .sortBy("ID").build());

        for (Pool pool : poolDefault.data()) {
            log.info("Pool: {} ", pool.id()+" isDefault: "+ pool.isDefault());
        }

         */
    }

    @Test
    void createPool() {
        //Auth
        DeviceParkApiClient client = DeviceParkApiClient.builder()
                .url("https://dev-devicepark.testinium.io")
                .credentials(Credentials.of(DEVICEPARK_CLIENT_ID, DEVICEPARK_CLIENT_SECRET))
                .build();
        
        /*
        CreatePoolRequest request = CreatePoolRequest.builder()
                .name("Android Regression")
                .build();

        Pool pool = client.pools().getDefaultPool(request);
        System.out.println(pool.id() + " - " + pool.name());

         */

    }

    @Test
    void hlsVideo() {
        DeviceParkApiClient client = DeviceParkApiClient.builder()
                .url("https://dev-devicepark.testinium.io")
                .credentials(Credentials.of(DEVICEPARK_CLIENT_ID, DEVICEPARK_CLIENT_SECRET))
                .build();

        DeviceSessionFilterRequest sessionFilter = DeviceSessionFilterRequest.of(
                SessionFilter.SESSION,
                "2ce682e7-b686-4e35-ae97-03e4b6564870",
                SearchOperation.EQUAL
        );

        PageDto<Session> page = client.sessions().list(
                DeviceSessionRequest.builder()
                        .filters(Collections.singletonList(sessionFilter))
                        .page(0)
                        .size(1)
                        .build()
        );

        Session session = page.data().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Session was not found"));

        if (!"CLOSED".equals(session.state()) || session.videoRecordUrl() == null) {
            throw new IllegalStateException("The session recording is not ready yet");
        }

        System.out.println(session.videoRecordUrl());

        try {
            System.out.println(readHlsPlaylist(session, "eyJraWQiOiJmNjJiNzc1MS01NDVjLTQwZGEtOGJhNC0xNWU2ZjE0ZDU0MDQiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJNZWhtZXRfQXBwaXVtR1dfQ2xpZW50IiwiYXVkIjoiTWVobWV0X0FwcGl1bUdXX0NsaWVudCIsIm5iZiI6MTc4NzA1NzIzMCwic2NvcGUiOlsiZXh0ZXJuYWwtY2xpZW50LXNjb3BlIiwib3BlbmlkIl0sImlzcyI6Imh0dHBzOi8vZGV2LWRldmljZXBhcmstaW50ZXJuYWwudGVzdGluaXVtLmlvL3VhYSIsImV4cCI6MTc4NzA2NDQzMCwiZW50aXR5X2lkIjoiOTAiLCJpYXQiOjE3ODcwNTcyMzAsImp0aSI6ImM3MjM4YjFjLTdiZDQtNDFiZi1iOGQxLTAxNzA4YzcxZTQ2YyJ9.L4wzV6LEurtsJANDW5MLEwUabPU-iKCdE8XL6ArzudKphLUdA2qErTAVO9OF__7w7WUwUzblVmrHXQEy6n_XoLJxQVqsgH8_FJZ3PVNgGJaFP-8Cf_fL2t2RSba976jirVZkOHfaI-5oL8nz9amSWuY1oJ-NS4xRUqajXL00IZo3gCWymCnRSqo8VtieK_cvQb4sJvjo6i808eb84o5Zr31TXvxlwLto5hFrRp5AV8GspYP0yk2lAwJ-IqNEHPGFO5tkMY-xK_obcNVBzeZO5cUFk1LE9INS8mjNslYhU-3UNNzZwy3ZtGa_375-wtv0Kod57e6NL2ZrGAWagKvM1g"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String readHlsPlaylist(Session session, String bearerToken) throws IOException {
        if (session.videoRecordUrl() == null) {
            throw new IllegalStateException("The session recording is not ready yet");
        }

        URL hlsUrl = new URL(session.videoRecordUrl());
        HttpURLConnection connection = (HttpURLConnection) hlsUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/vnd.apple.mpegurl");
        connection.setRequestProperty("Authorization", "Bearer " + bearerToken);

        int status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            throw new IOException("HLS playlist request failed with HTTP " + status);
        }

        StringBuilder playlist = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                playlist.append(line).append('\n');
            }
        } finally {
            connection.disconnect();
        }

        if (playlist.indexOf("#EXTM3U") != 0) {
            throw new IOException("Device Park did not return an HLS playlist");
        }

        return playlist.toString();
    }
}