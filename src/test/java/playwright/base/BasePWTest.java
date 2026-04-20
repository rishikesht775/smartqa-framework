package playwright.base;

import com.microsoft.playwright.*;

public class BasePWTest {

    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static void setupPW(String browserName) {

        playwright.set(Playwright.create());

        BrowserType browserType;

        switch (browserName.toLowerCase()) {

            case "firefox":
                browserType = playwright.get().firefox();
                break;

            case "webkit":
                browserType = playwright.get().webkit();
                break;

            case "chrome":
            case "chromium":
            default:
                browserType = playwright.get().chromium();
                break;
        }

        browser.set(
            browserType.launch(
                new BrowserType.LaunchOptions().setHeadless(false)
            )
        );

        context.set(browser.get().newContext());
        page.set(context.get().newPage());
    }

    public static Page getPage() {
        return page.get();
    }

    public static void tearDownPW() {

        if (context.get() != null) context.get().close();
        if (browser.get() != null) browser.get().close();
        if (playwright.get() != null) playwright.get().close();

        context.remove();
        browser.remove();
        playwright.remove();
        page.remove();
    }
}