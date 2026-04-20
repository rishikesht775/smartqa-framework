package stepdefinitions;

import io.cucumber.java.*;
import io.cucumber.java.Scenario;

import base.BaseTest;
import playwright.base.BasePWTest;
import utils.ScreenshotUtil;

import com.aventstack.extentreports.MediaEntityBuilder;

public class Hooks {

    // 🔥 Thread-safe browser storage
    private static ThreadLocal<String> browser = new ThreadLocal<>();

    // ✅ Capture browser
    @Before(order = 0)
    public void setBrowser(Scenario scenario) {

        String browserName = System.getProperty("browser");

        if (browserName == null || browserName.isEmpty()) {
            browserName = "chrome"; // default
        }

        browser.set(browserName);

        // ❌ REMOVE THIS (IMPORTANT)
        // ExtentHooks.test.set(...)
        // 👉 ExtentHooks will handle it
    }

    // ✅ Selenium Setup
    @Before(value = "@selenium", order = 1)
    public void setupSelenium() {

        String browserName = browser.get();

        if (browserName != null &&
                (browserName.equalsIgnoreCase("chrome")
                        || browserName.equalsIgnoreCase("edge"))) {

            BaseTest.setup(browserName);
        }
    }

    // ✅ Playwright Setup
    @Before(value = "@playwright", order = 1)
    public void setupPlaywright() {

        String browserName = browser.get();

        // 🔥 Fix mapping
        if (browserName != null) {

            String pwBrowser = "chromium"; // default

            if (browserName.equalsIgnoreCase("firefox")) {
                pwBrowser = "firefox";
            } else if (browserName.equalsIgnoreCase("webkit")) {
                pwBrowser = "webkit";
            }

            BasePWTest.setupPW(pwBrowser);
        }
    }

    // ✅ Selenium Teardown
    @After(value = "@selenium", order = 1)
    public void tearDownSelenium() {

        String browserName = browser.get();

        if (browserName != null &&
                (browserName.equalsIgnoreCase("chrome")
                        || browserName.equalsIgnoreCase("edge"))) {

            BaseTest.tearDown();
        }
    }

    // ✅ Playwright Teardown
    @After(value = "@playwright", order = 1)
    public void tearDownPW() {

        String browserName = browser.get();

        if (browserName != null) {
            BasePWTest.tearDownPW();
        }
    }

    // ✅ Screenshot + Reporting
    @After(order = 2)
    public void screenshot(Scenario scenario) {

        String browserName = browser.get();

        if (browserName == null) browserName = "unknown";

        String screenshotName = scenario.getName() + "_" + browserName;

        String path = ScreenshotUtil.capture(screenshotName);

        if (path != null && ExtentHooks.test.get() != null) {

            if (scenario.isFailed()) {
                ExtentHooks.test.get()
                        .fail("❌ Test Failed",
                                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            } else {
                ExtentHooks.test.get()
                        .pass("✅ Test Passed",
                                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            }
        }
    }

    // ✅ Clean ThreadLocal
    @After(order = 3)
    public void cleanUp() {
        browser.remove();
    }
}