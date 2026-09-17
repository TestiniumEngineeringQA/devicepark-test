package action;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Interactive;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class Swipe {

    public static void swipeLeft(WebDriver driver) {

        Dimension size = driver.manage().window().getSize();

        int startX = (int) (size.width * 0.80);
        int endX = (int) (size.width * 0.20);

        int y = size.height / 2;

        PointerInput finger = new PointerInput(
                PointerInput.Kind.TOUCH,
                "finger"
        );

        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(200),
                        PointerInput.Origin.viewport(),
                        startX,
                        y
                )
        );

        swipe.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(800),
                        PointerInput.Origin.viewport(),
                        endX,
                        y
                )
        );

        swipe.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        ((Interactive) driver).perform(List.of(swipe));
    }

    public static void swipeRight(WebDriver driver) {

        Dimension size = driver.manage().window().getSize();

        int startX = (int) (size.width * 0.20);
        int endX = (int) (size.width * 0.80);

        int y = size.height / 2;

        PointerInput finger = new PointerInput(
                PointerInput.Kind.TOUCH,
                "finger"
        );

        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(200),
                        PointerInput.Origin.viewport(),
                        startX,
                        y
                )
        );

        swipe.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(800),
                        PointerInput.Origin.viewport(),
                        endX,
                        y
                )
        );

        swipe.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        ((Interactive) driver).perform(List.of(swipe));
    }

    public static void swipeUp(WebDriver driver) {

        Dimension size = driver.manage().window().getSize();

        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);

        int endX = startX;
        int endY = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(
                PointerInput.Kind.TOUCH,
                "finger"
        );

        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                startX,
                startY
        ));

        swipe.addAction(finger.createPointerDown(
                PointerInput.MouseButton.LEFT.asArg()
        ));

        swipe.addAction(finger.createPointerMove(
                Duration.ofMillis(700),
                PointerInput.Origin.viewport(),
                endX,
                endY
        ));

        swipe.addAction(finger.createPointerUp(
                PointerInput.MouseButton.LEFT.asArg()
        ));

        ((org.openqa.selenium.interactions.Interactive) driver)
                .perform(Collections.singletonList(swipe));
    }

    public static void swipeDown(WebDriver driver) {

        Dimension size = driver.manage().window().getSize();

        int startX = size.width / 2;
        int startY = (int) (size.height * 0.2);

        int endX = startX;
        int endY = (int) (size.height * 0.8);

        PointerInput finger = new PointerInput(
                PointerInput.Kind.TOUCH,
                "finger"
        );

        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                startX,
                startY
        ));

        swipe.addAction(finger.createPointerDown(
                PointerInput.MouseButton.LEFT.asArg()
        ));

        swipe.addAction(finger.createPointerMove(
                Duration.ofMillis(700),
                PointerInput.Origin.viewport(),
                endX,
                endY
        ));

        swipe.addAction(finger.createPointerUp(
                PointerInput.MouseButton.LEFT.asArg()
        ));

        ((Interactive) driver).perform(
                Collections.singletonList(swipe)
        );
    }
}
