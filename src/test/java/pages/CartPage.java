package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import driver.DriverFactory;
import utils.WaitUtils;

public class CartPage {

    WebDriver driver = DriverFactory.getDriver();

    // Locator for product inside cart
    By cartItem = By.className("inventory_item_name");

    public boolean isProductDisplayedInCart() {
        WaitUtils.waitForElementVisible(cartItem);
        return driver.findElement(cartItem).isDisplayed();
    }
}