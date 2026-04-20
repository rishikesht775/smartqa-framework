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

    // 🔹 Actions

    public void checkout(String f, String l, String zip) {

        // ✅ Step 1: Click checkout (cart page)
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();

        // ✅ Step 2: Fill details
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(f);
        driver.findElement(lastName).sendKeys(l);
        driver.findElement(postalCode).sendKeys(zip);
        driver.findElement(continueBtn).click();

        // ✅ Step 3: Wait for step 2 page
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));

        System.out.println("Current URL: " + driver.getCurrentUrl());

        // ✅ Step 4: Click Finish
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();

        // 🔥 VERY IMPORTANT → wait for success page
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
    }

    // 🔹 Validation
    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).getText();
    }
}