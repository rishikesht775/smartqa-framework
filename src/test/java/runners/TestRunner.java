package runners;

import io.cucumber.testng.*;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",   // ✅ keep simple (auto picks sub-packages)
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true) // 🔥 scenario parallel
    public Object[][] scenarios() {
        return super.scenarios();
    }
}