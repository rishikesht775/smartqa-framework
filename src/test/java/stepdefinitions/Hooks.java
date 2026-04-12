package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import base.BaseTest;
import utils.ScreenshotUtils;

public class Hooks extends BaseTest {

    @Before
    public void setup() {
        System.out.println("HOOK BEFORE RUNNING");
        super.setup();
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {

            String path = ScreenshotUtils.captureScreenshot(scenario.getName());
            System.out.println("Screenshot saved at: " + path);
        }

        System.out.println("HOOK AFTER RUNNING");
        super.tearDown();
    }
}