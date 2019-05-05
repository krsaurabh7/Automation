package cucumber;

import managers.PageObjectManager;
import managers.WebdriverManager;

import java.io.IOException;

public class TestContext {
    private WebdriverManager webDriverManager;
    private PageObjectManager pageObjectManager;

    public TestContext() throws IOException {
        webDriverManager = new WebdriverManager();
        pageObjectManager = new PageObjectManager(webDriverManager.getDriver());
    }

    public WebdriverManager getWebDriverManager() {
        return webDriverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

}
