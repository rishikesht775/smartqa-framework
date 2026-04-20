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
        ExtentHooks.test.get().info("Opened Selenium login page");
    }

    @When("user logs in using selenium")
    public void login() {
        login.login("standard_user", "secret_sauce");
    }

    @When("user adds product to cart using selenium")
    public void add() {
        product.addProductToCart();
    }

    @When("user opens cart using selenium")
    public void openCart() {
        product.openCart();
    }

    @Then("cart should have 1 item using selenium")
    public void validateCart() {
        Assert.assertEquals(cart.getCartCount(), 1);
    }

    @When("user completes checkout using selenium")
    public void checkoutStep() {
    	product.openCart();
        checkout.checkout("Rishi", "QA", "560001");
    }

    @Then("order should be placed successfully using selenium")
    public void validateOrder() {
        Assert.assertEquals(checkout.getSuccessMessage(), "Thank you for your order!");
    }
}