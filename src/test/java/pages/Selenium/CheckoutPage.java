package pages.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class CheckoutPage {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // 🔹 Locators
    
    By checkoutBtn = By.id("checkout");
    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By continueBtn = By.id("continue");
    By finishBtn = By.id("finish");
    By successMsg = By.className("complete-header");

    // 🔹 Actions

    public void startCheckout() {
        driver.findElement(checkoutBtn).click();
    }

    public void enterDetails(String f, String l, String zip) {
        driver.findElement(firstName).sendKeys(f);
        driver.findElement(lastName).sendKeys(l);
        driver.findElement(postalCode).sendKeys(zip);
        driver.findElement(continueBtn).click();
    }

    public void finishOrder() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ✅ WAIT until Finish button is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(finishBtn));

        driver.findElement(finishBtn).click();
    }
    
    public void checkout(String f, String l, String zip) {

        // Step 1: Click Checkout (from cart page)
        startCheckout();

        // Step 2: Fill details
        enterDetails(f, l, zip);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // ✅ CRITICAL: Wait for Step 2 page
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));

        // DEBUG (optional)
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Step 3: Click Finish
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }
    // 🔹 Validation
    public String getSuccessMessage() {
        return driver.findElement(successMsg).getText();
    }

   
}