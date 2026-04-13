package stepdefinitions;

import com.aventstack.extentreports.*;
import io.cucumber.java.*;

import utils.ExtentManager;

public class ExtentHooks {

    static ExtentReports extent = ExtentManager.getInstance();
    static ExtentTest test;

    @Before
    public void beforeScenario(Scenario scenario) {
    	test = extent.createTest("Selenium - " + scenario.getName());
    }
    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            test.fail("Scenario Failed: " + scenario.getName());
        } else {
            test.pass("Scenario Passed: " + scenario.getName());
        }

        extent.flush();  // 🔥 keep this
    }
    @After
    public void afterScenario() {
        extent.flush();
    }
}