package playwright.base;

import com.microsoft.playwright.*;

public class BasePWTest {

    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static void setupPW(String browserName) {

        // 🔥 Safety: avoid null browserName
        if (browserName == null) {
            browserName = "chromium";
        }

        Playwright pw = Playwright.create();
        playwright.set(pw);

        BrowserType browserType;

        switch (browserName.toLowerCase()) {
            case "firefox":
                browserType = pw.firefox();
                break;
            case "webkit":
                browserType = pw.webkit();
                break;
            default:
                browserType = pw.chromium();
        }

        Browser br = browserType.launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        browser.set(br);

        BrowserContext ctx = br.newContext();
        context.set(ctx);

        Page pg = ctx.newPage();
        page.set(pg);  // 🔥 CRITICAL: must be set

        // 🔥 DEBUG (optional)
        System.out.println("Playwright browser started: " + browserName);
    }

    public static Page getPage() {

        if (page.get() == null) {
            throw new RuntimeException("❌ Playwright Page is NULL! setupPW() not called.");
        }

        return page.get();
    }

    public static void tearDownPW() {

        try {
            if (page.get() != null) {
                page.get().close();
            }

            if (context.get() != null) {
                context.get().close();
            }

            if (browser.get() != null) {
                browser.get().close();
            }

            if (playwright.get() != null) {
                playwright.get().close();
            }

        } catch (Exception e) {
            System.out.println("⚠ Error during Playwright teardown: " + e.getMessage());
        }

        // 🔥 VERY IMPORTANT (for parallel execution)
        page.remove();
        context.remove();
        browser.remove();
        playwright.remove();
    }
}