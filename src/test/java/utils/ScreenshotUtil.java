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

            // ✅ Create folder if not exists
            String dirPath = System.getProperty("user.dir") + "/test-output/screenshots/";
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filePath = dirPath + name.replaceAll(" ", "_") + ".png";

            // ✅ Selenium
            if (DriverFactory.getDriver() != null) {

                WebDriver driver = DriverFactory.getDriver();

                File src = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(src, new File(filePath));
            }

            // ✅ Playwright
            else if (BasePWTest.getPage() != null) {

                Page page = BasePWTest.getPage();

                page.screenshot(new Page.ScreenshotOptions()
                        .setPath(Paths.get(filePath)));
            }

            // 🔥 RETURN ABSOLUTE PATH (CRITICAL)
            return filePath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}