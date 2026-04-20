package base;

import driver.DriverFactory;

public class BaseTest {

    // 🔥 Accept browser dynamically
    public static void setup(String browser) {
        DriverFactory.initDriver(browser);
    }

    public static void tearDown() {
        DriverFactory.quitDriver();
    }
}