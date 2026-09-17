package action;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.Map;

public class SwipeLast {

    public static void swipeLeftLast(WebDriver driver) {

        Map<String, Object> params = new HashMap<>();
        params.put("direction", "left");

        ((JavascriptExecutor) driver)
                .executeScript("mobile: swipe", params);
    }


    public static void swipeRightLast(WebDriver driver) {

        Map<String, Object> params = new HashMap<>();
        params.put("direction", "right");

        ((JavascriptExecutor) driver)
                .executeScript("mobile: swipe", params);
    }
}
