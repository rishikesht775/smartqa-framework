package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;

import pages.Playwright.*;
import playwright.base.BasePWTest;

public class PlaywrightSteps {

    PWLoginPage login;
    PWProductPage product;
    PWCartPage cart;
    PWCheckoutPage checkout;

    @Given("user is on playwright login page")
    public void open() {

        login = new PWLoginPage(BasePWTest.getPage());
        product = new PWProductPage(BasePWTest.getPage());
        cart = new PWCartPage(BasePWTest.getPage());
        checkout = new PWCheckoutPage(BasePWTest.getPage());

        login.openURL();
        ExtentHooks.test.get().info("🌐 Opened Playwright login page");
    }

    @When("user logs in using playwright")
    public void login() {
        login.login("standard_user", "secret_sauce");
        ExtentHooks.test.get().info("🔐 Logged in using Playwright");
    }

    @When("user adds product to cart using playwright")
    public void add() {
        product.addProductToCart();
        ExtentHooks.test.get().info("🛒 Product added to cart (PW)");
    }

    @When("user opens cart using playwright")
    public void openCart() {
        product.openCart();
        ExtentHooks.test.get().info("📦 Opened cart page (PW)");
    }

    @Then("cart should have 1 item using playwright")
    public void validateCart() {
        Assert.assertEquals(cart.getCartCount(), 1);
        ExtentHooks.test.get().pass("✅ Cart validated (PW)");
    }

    @When("user completes checkout using playwright")
    public void checkoutStep() {

    	product.openCart();

        checkout.checkout("Rishi", "QA", "560001");
        ExtentHooks.test.get().info("💳 Checkout completed (PW)");
    }

    @Then("order should be placed successfully using playwright")
    public void validateOrder() {

        String msg = checkout.getSuccessMessage();

        Assert.assertTrue(msg.contains("Thank you"));
        ExtentHooks.test.get().pass("🎉 Order placed successfully (PW)");
    }
}