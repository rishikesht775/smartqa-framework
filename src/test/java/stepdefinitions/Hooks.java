package stepdefinitions;

import io.cucumber.java.*;
import base.BaseTest;
import playwright.base.BasePWTest;
import utils.ScreenshotUtil;

public class Hooks {

    @Before("@selenium")
    public void setupSelenium() {
        BaseTest.setup();
    }

    @Before("@playwright")
    public void setupPlaywright() {

        String browser = System.getProperty("browser", "chromium");

        BasePWTest.setupPW(browser);
    }

    @After("@selenium")
    public void tearDownSelenium() {
        BaseTest.tearDown();
    }

    @After("@playwright")
    public void tearDownPW() {
        BasePWTest.tearDownPW();
    }

    @After
    public void screenshot(Scenario scenario) {

        String path = ScreenshotUtil.capture(scenario.getName());

        if (scenario.isFailed()) {
            ExtentHooks.test.get()
                .fail("Test Failed")
                .addScreenCaptureFromPath(path);
        } else {
            ExtentHooks.test.get()
                .pass("Test Passed")
                .addScreenCaptureFromPath(path);
        }
    }
}