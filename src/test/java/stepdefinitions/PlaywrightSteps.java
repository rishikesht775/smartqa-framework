package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;

import pages.Playwright.PWLoginPage;
import pages.Playwright.PWProductPage;
import pages.Playwright.PWCartPage;

import playwright.base.BasePWTest;

public class PlaywrightSteps {

    PWLoginPage login;
    PWProductPage product;
    PWCartPage cart;

    @Given("user is on playwright login page")
    public void open() {

        login = new PWLoginPage(BasePWTest.getPage());
        product = new PWProductPage(BasePWTest.getPage());
        cart = new PWCartPage(BasePWTest.getPage());

        login.openURL();
        ExtentHooks.test.get().info("Opened Playwright login page");
    }

    @When("user logs in using playwright")
    public void login() {
        login.login("standard_user", "secret_sauce");
        ExtentHooks.test.get().info("Logged in using Playwright");
    }

    @When("user adds product to cart using playwright")
    public void add() {
        product.addProductToCart();
        ExtentHooks.test.get().info("Added product to cart (Playwright)");
    }

    @Then("cart should have 1 item using playwright")
    public void validate() {
        Assert.assertEquals(cart.getCartCount(), 1);
        ExtentHooks.test.get().pass("Cart validated (Playwright)");
    }
}