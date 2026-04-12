package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import driver.DriverFactory;
import utils.WaitUtils;

public class ProductPage {

    WebDriver driver = DriverFactory.getDriver();

    // Locators
    By addToCart = By.id("add-to-cart-sauce-labs-backpack");
    By cartIcon = By.className("shopping_cart_link");
    By cartBadge = By.className("shopping_cart_badge");

    // Actions
    public void addProductToCart() {
        WaitUtils.waitForElementVisible(addToCart);
        driver.findElement(addToCart).click();
    }

    public void openCart() {
        WaitUtils.waitForElementVisible(cartIcon);
        driver.findElement(cartIcon).click();
    }

    // 🔥 VALIDATION METHODS
    public String getCartCount() {
        WaitUtils.waitForElementVisible(cartBadge);
        return driver.findElement(cartBadge).getText();
    }

    public boolean isCartIconDisplayed() {
        return driver.findElement(cartIcon).isDisplayed();
    }
}