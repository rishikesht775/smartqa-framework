package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",   // auto picks sub-packages
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        },
       //tags = "@selenium",   // ✅ FIX: use "tags" and proper syntax
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true) // 🔥 scenario parallel execution
    public Object[][] scenarios() {
        return super.scenarios();
    }
}