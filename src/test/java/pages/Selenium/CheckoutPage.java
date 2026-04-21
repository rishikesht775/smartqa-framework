package pages.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // 🔹 Locators
    By checkoutBtn = By.id("checkout");
    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By continueBtn = By.id("continue");
    By finishBtn = By.id("finish");
    By successMsg = By.className("complete-header");

    // 🔹 Main Checkout Flow
    public void checkout(String f, String l, String zip) {

        // ✅ Step 1: Click Checkout (WITH WAIT)
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();

        // ✅ Step 2: Fill Details (NO WAIT)
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(f);

        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(l);

        driver.findElement(postalCode).clear();
        driver.findElement(postalCode).sendKeys(zip);

        // ✅ Step 3: Click Continue (WITH WAIT)
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();

        // ✅ Step 4: Print URL
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // ✅ Step 5: Click Finish (WITH WAIT)
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    // 🔹 Validation (optional wait — can remove if not needed)
    public String getSuccessMessage() {
        return driver.findElement(successMsg).getText();
    }
}