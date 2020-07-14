package framework.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static framework.utils.Waiter.TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS;
import static framework.utils.Waiter.waitUntilExpected;

public abstract class BasePage implements Page {

    void waiForUrl(String url) {
        waitUntilExpected(webDriver ->
                getWebDriver().getCurrentUrl().equals(url), DEFAULT_TIMEOUT_5_000_MS);
    }

    void waiForHeader(SelenideElement header, String name) {
        waitUntilExpected(webDriver ->
                header.getText().equals(name), DEFAULT_TIMEOUT_5_000_MS);
    }
}
