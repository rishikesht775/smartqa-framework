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

            // ✅ Clean file name (important)
            String fileName = name.replaceAll("[^a-zA-Z0-9]", "_") + ".png";

            // ✅ Save OUTSIDE test-output
            String folderPath = System.getProperty("user.dir") + "/screenshots/";
            String fullPath = folderPath + fileName;

            // ✅ Create folder if not exists
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // ✅ Selenium case
            try {
                WebDriver driver = DriverFactory.getDriver();

                if (driver != null) {
                    File src = ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

                    FileUtils.copyFile(src, new File(fullPath));

                    // 🔥 RELATIVE PATH for Extent
                    return "../screenshots/" + fileName;
                }
            } catch (Exception ignored) {}

            // ✅ Playwright case
            try {
                Page page = BasePWTest.getPage();

                if (page != null) {
                    page.screenshot(new Page.ScreenshotOptions()
                            .setPath(Paths.get(fullPath)));

                    // 🔥 RELATIVE PATH for Extent
                    return "../screenshots/" + fileName;
                }
            } catch (Exception ignored) {}

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}