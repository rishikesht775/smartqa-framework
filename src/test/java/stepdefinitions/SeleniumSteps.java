package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;

import driver.DriverFactory;
import pages.Selenium.*;

public class SeleniumSteps {

    LoginPage login;
    ProductPage product;
    CartPage cart;
    CheckoutPage checkout;

    @Given("user is on selenium login page")
    public void open() {

        login = new LoginPage(DriverFactory.getDriver());
        product = new ProductPage(DriverFactory.getDriver());
        cart = new CartPage(DriverFactory.getDriver());
        checkout = new CheckoutPage(DriverFactory.getDriver());

        login.openURL();
        ExtentHooks.test.get().info("🌐 Opened Selenium login page");
    }

    @When("user logs in using selenium")
    public void login() {
        login.login("standard_user", "secret_sauce");
        ExtentHooks.test.get().info("🔐 Logged in successfully");
    }

    @When("user adds product to cart using selenium")
    public void add() {
        product.addProductToCart();
        ExtentHooks.test.get().info("🛒 Product added to cart");
    }

    @When("user opens cart using selenium")
    public void openCart() {
        product.openCart();
        ExtentHooks.test.get().info("📦 Opened cart page");
    }

    @Then("cart should have 1 item using selenium")
    public void validateCart() {
        Assert.assertEquals(cart.getCartCount(), 1);
        ExtentHooks.test.get().pass("✅ Cart has 1 item");
    }

    @When("user completes checkout using selenium")
    public void checkoutStep() {

    	product.openCart();

        checkout.checkout("Rishi", "QA", "560001");
        ExtentHooks.test.get().info("💳 Checkout completed");
    }

    @Then("order should be placed successfully using selenium")
    public void validateOrder() {

        String msg = checkout.getSuccessMessage();

        Assert.assertTrue(msg.contains("Thank you"));
        ExtentHooks.test.get().pass("🎉 Order placed successfully");
    }
}