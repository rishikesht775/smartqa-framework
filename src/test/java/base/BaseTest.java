package base;


import driver.DriverFactory;

public class BaseTest {

    public static void setup() {
        DriverFactory.initDriver("chrome");   // ✅ USE THIS
    }

    public static void tearDown() {
        DriverFactory.quitDriver();           // ✅ USE THIS
    }
}