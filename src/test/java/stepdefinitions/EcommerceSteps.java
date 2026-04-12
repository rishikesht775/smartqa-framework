package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.*;

public class EcommerceSteps {

    LoginPage login = new LoginPage();
    ProductPage product = new ProductPage();
    CartPage cart = new CartPage();

    @Given("user is on login page")
    public void openLoginPage() {
        System.out.println("User on login page");
    }

    @When("user logs in with valid credentials")
    public void loginUser() {
        System.out.println("Logging in...");
        login.login("standard_user", "secret_sauce");
    }

    @When("user adds product to cart")
    public void addToCart() {

        product.addProductToCart();

        // 🔥 ASSERTION 1: Cart count = 1
        String count = product.getCartCount();
        System.out.println("Cart count: " + count);

        Assert.assertEquals(count, "1", "❌ Product NOT added to cart!");
    }

    @Then("cart should be opened")
    public void openCart() {

        product.openCart();

        // 🔥 ASSERTION 2: Product visible in cart
        boolean isDisplayed = cart.isProductDisplayedInCart();

        Assert.assertTrue(isDisplayed, "❌ Product not visible in cart!");
    }
}