package base;

import driver.DriverFactory;
import utils.ConfigReader;

public class BaseTest {

    ConfigReader config = new ConfigReader();

    public void setup() {

        System.out.println("BaseTest setup started");

        String browser = config.getBrowser();
        String url = config.getUrl();

        System.out.println("Browser from config: " + browser);
        System.out.println("URL from config: " + url);

        DriverFactory.initDriver(browser);

        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().get(url);
    }

    public void tearDown() {
        System.out.println("Closing browser");
        DriverFactory.quitDriver();   // ✅ use safe quit
    }
}