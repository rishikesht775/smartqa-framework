package utils;

import org.openqa.selenium.*;
import java.io.File;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import driver.DriverFactory;
import playwright.base.BasePWTest;
import com.microsoft.playwright.Page;

public class ScreenshotUtil {

    public static String capture(String name) {

        try {

            String path = "screenshots/" + name + ".png";

            // ✅ Selenium case
            if (DriverFactory.getDriver() != null) {

                WebDriver driver = DriverFactory.getDriver();

                File src = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(src, new File(path));
            }

            // ✅ Playwright case
            else if (BasePWTest.getPage() != null) {

                Page page = BasePWTest.getPage();

                page.screenshot(new Page.ScreenshotOptions()
                        .setPath(Paths.get(path)));
            }

            return path;

        } catch (Exception e) {
            return null;
        }
    }
}