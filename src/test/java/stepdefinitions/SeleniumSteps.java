package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;

import driver.DriverFactory;
import pages.Selenium.LoginPage;
import pages.Selenium.ProductPage;
import pages.Selenium.CartPage;

import stepdefinitions.ExtentHooks;

public class SeleniumSteps {

    LoginPage login;
    ProductPage product;
    CartPage cart;

    @Given("user is on selenium login page")
    public void open() {

        login = new LoginPage(DriverFactory.getDriver());
        product = new ProductPage(DriverFactory.getDriver());
        cart = new CartPage(DriverFactory.getDriver());

        login.openURL();
        ExtentHooks.test.get().info("Opened Selenium login page");
    }

    @When("user logs in using selenium")
    public void login() {
        login.login("standard_user", "secret_sauce");
        ExtentHooks.test.get().info("Logged in using Selenium");
    }

    @When("user adds product to cart using selenium")
    public void add() {
        product.addProductToCart();
        ExtentHooks.test.get().info("Added product to cart (Selenium)");
    }

    @Then("cart should have 1 item using selenium")
    public void validate() {
        Assert.assertEquals(cart.getCartCount(), 1);
        ExtentHooks.test.get().pass("Cart validated (Selenium)");
    }
}