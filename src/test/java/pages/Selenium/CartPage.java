package pages.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    By cartBadge = By.className("shopping_cart_badge");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartCount() {
        String count = driver.findElement(cartBadge).getText();
        return Integer.parseInt(count);
    }
}