package pages.Playwright;

import com.microsoft.playwright.Page;

public class PWCartPage {

    Page page;

    public PWCartPage(Page page) {
        this.page = page;
    }

    public int getCartCount() {
        return Integer.parseInt(page.textContent(".shopping_cart_badge"));
    }
}