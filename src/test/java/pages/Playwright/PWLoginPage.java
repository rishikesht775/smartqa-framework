package pages.Playwright;

import com.microsoft.playwright.Page;

public class PWLoginPage {

    Page page;

    public PWLoginPage(Page page) {
        this.page = page;
    }

    public void openURL() {
        page.navigate("https://www.saucedemo.com/");
    }

    public void login(String user, String pass) {
        page.fill("#user-name", user);
        page.fill("#password", pass);
        page.click("#login-button");
    }
}