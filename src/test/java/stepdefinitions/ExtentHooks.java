package stepdefinitions;

import io.cucumber.java.*;
import com.aventstack.extentreports.*;
import utils.ExtentManager;

public class ExtentHooks {

    public static ExtentReports extent = ExtentManager.getInstance();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Before(order = 0)
    public void beforeScenario(Scenario scenario) {

        // 🔥 Create Extent test per scenario (thread-safe)
        ExtentTest extentTest = extent.createTest(scenario.getName());

        test.set(extentTest);
    }

    @After(order = 0)
    public void afterScenario(Scenario scenario) {

        // 🔥 Optional: log final status
        if (test.get() != null) {
            if (scenario.isFailed()) {
                test.get().fail("❌ Scenario Failed");
            } else {
                test.get().pass("✅ Scenario Passed");
            }
        }

        // 🔥 Flush AFTER EACH SCENARIO (safer than AfterAll)
        extent.flush();

        // 🔥 Clean thread
        test.remove();
    }
}