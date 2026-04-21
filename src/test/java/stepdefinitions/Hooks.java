package stepdefinitions;

import io.cucumber.java.*;
import io.cucumber.java.Scenario;

import base.BaseTest;
import playwright.base.BasePWTest;
import utils.ScreenshotUtil;

import com.aventstack.extentreports.MediaEntityBuilder;

public class Hooks {

    private static ThreadLocal<String> browser = new ThreadLocal<>();

    // ✅ Capture browser (from CLI / Jenkins / default)
    @Before(order = 0)
    public void setBrowser(Scenario scenario) {

        String browserName = System.getProperty("browser");

        if (browserName == null || browserName.isEmpty()) {
            browserName = "chrome"; // default
        }

        browser.set(browserName.toLowerCase());
    }

    // ✅ Selenium Setup (chrome / edge)
    @Before(value = "@selenium", order = 1)
    public void setupSelenium() {

        String browserName = browser.get();

        // 🔥 Only Selenium-supported browsers
        if (browserName.equals("chrome") || browserName.equals("edge")) {
            BaseTest.setup(browserName);
        }
    }

    // ✅ Playwright Setup (chromium / firefox / webkit)
    @Before(value = "@playwright", order = 1)
    public void setupPlaywright() {

        String browserName = browser.get();

        String pwBrowser = "chromium"; // default

        if (browserName.equals("firefox")) {
            pwBrowser = "firefox";
        } else if (browserName.equals("webkit")) {
            pwBrowser = "webkit";
        }

        BasePWTest.setupPW(pwBrowser);
    }

    // ✅ Selenium Teardown
    @After(value = "@selenium", order = 1)
    public void tearDownSelenium() {
        BaseTest.tearDown();
    }

    // ✅ Playwright Teardown
    @After(value = "@playwright", order = 1)
    public void tearDownPW() {
        BasePWTest.tearDownPW();
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

    // ✅ Cleanup
    @After(order = 3)
    public void cleanUp() {
        browser.remove();
    }
}