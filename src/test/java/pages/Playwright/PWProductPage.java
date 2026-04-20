package pages.Playwright;

import com.microsoft.playwright.Page;

public class PWProductPage {

    Page page;

    // ✅ ADD THIS CONSTRUCTOR
    public PWProductPage(Page page) {
        this.page = page;
    }

    public void addProductToCart() {
        page.click("#add-to-cart-sauce-labs-backpack");
    }

    public void openCart() {
        page.click(".shopping_cart_link");
    }
}