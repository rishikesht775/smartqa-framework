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
        ExtentHooks.test.get().info("Opened Playwright login page");
    }

    @When("user logs in using playwright")
    public void login() {
        login.login("standard_user", "secret_sauce");
    }

    @When("user adds product to cart using playwright")
    public void add() {
        product.addProductToCart();
    }

    @When("user opens cart using playwright")
    public void openCart() {
        product.openCart();
    }

    @Then("cart should have 1 item using playwright")
    public void validateCart() {
        Assert.assertEquals(cart.getCartCount(), 1);
    }

    @When("user completes checkout using playwright")
    public void checkoutStep() {
    	
    	product.openCart();
        checkout.checkout("Rishi", "QA", "560001");
    }

    @Then("order should be placed successfully using playwright")
    public void validateOrder() {
        Assert.assertEquals(checkout.getSuccessMessage(), "Thank you for your order!");
    }
}