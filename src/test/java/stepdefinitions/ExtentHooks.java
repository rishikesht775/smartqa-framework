package stepdefinitions;

import com.aventstack.extentreports.*;
import io.cucumber.java.*;

import utils.ExtentManager;

public class ExtentHooks {

    static ExtentReports extent = ExtentManager.getInstance();
    static ExtentTest test;

    @Before
    public void beforeScenario(Scenario scenario) {
        test = extent.createTest(scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {

        if (scenario.isFailed()) {
            test.fail("Step Failed");
        } else {
            test.pass("Step Passed");
        }
    }

    @After
    public void afterScenario() {
        extent.flush();
    }
}