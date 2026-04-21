package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {

        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        browser = browser.toLowerCase();

        switch (browser) {

            case "edge":

                WebDriverManager.edgedriver().setup();

                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications");

                driver.set(new EdgeDriver(edgeOptions));
                break;

            case "chrome":
            default:

                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                // 🔥 MUST FIX (disable password + breach popup)
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);

                options.setExperimentalOption("prefs", prefs);

                // 🔥 VERY IMPORTANT FLAGS
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-save-password-bubble");
                options.addArguments("--disable-infobars");
                options.addArguments("--start-maximized");

                // 🔥 USE FRESH PROFILE (CRITICAL)
                options.addArguments("--incognito");

                driver.set(new ChromeDriver(options));
                break;
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {

        WebDriver dr = driver.get();

        if (dr != null) {
            dr.quit();       // ✅ closes browser completely
            driver.remove(); // ✅ removes ThreadLocal (VERY IMPORTANT)
        }
    }
}