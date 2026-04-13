package playwright.tests;

import org.testng.annotations.*;
import com.aventstack.extentreports.*;

import playwright.base.BasePWTest;
import utils.ExtentManager;

import java.lang.reflect.Method;

public class LoginTest extends BasePWTest {

    ExtentReports extent = ExtentManager.getInstance();
    ExtentTest test;

    @BeforeMethod
    public void start(Method method) {
        setup();
        test = extent.createTest(method.getName());
    }

    @Test
    public void loginTest() {

        page.navigate("https://www.saucedemo.com/");
        test.info("Navigated to login page");

        page.fill("#user-name", "standard_user");
        test.info("Entered username");

        page.fill("#password", "secret_sauce");
        test.info("Entered password");

        page.click("#login-button");
        test.pass("Login successful 🚀");
    }

    @AfterMethod
    public void end() {
        tearDown();
        extent.flush();
    }
}