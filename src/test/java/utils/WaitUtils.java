package utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import driver.DriverFactory;

public class WaitUtils {

    public static void waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(
                DriverFactory.getDriver(),
                Duration.ofSeconds(10)
        );
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}