package stepdefinitions;

import io.cucumber.java.*;
import com.aventstack.extentreports.*;
import utils.ExtentManager;

public class ExtentHooks {

    public static ExtentReports extent = ExtentManager.getInstance();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Before
    public void beforeScenario(Scenario scenario) {
        test.set(extent.createTest(scenario.getName()));
    }

    @After
    public void afterScenario() {
        extent.flush();
    }
}