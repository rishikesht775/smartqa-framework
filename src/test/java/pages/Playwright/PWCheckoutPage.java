package pages.Playwright;

import com.microsoft.playwright.Page;

public class PWCheckoutPage {

    Page page;

    public PWCheckoutPage(Page page) {
        this.page = page;
    }

    public void checkout(String f, String l, String z) {
        page.click("#checkout");
        page.fill("#first-name", f);
        page.fill("#last-name", l);
        page.fill("#postal-code", z);
        page.click("#continue");
        page.click("#finish");
    }

    public String getSuccessMessage() {
        return page.textContent(".complete-header");
    }
}