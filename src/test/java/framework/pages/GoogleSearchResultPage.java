package framework.pages;

import com.codeborne.selenide.SelenideElement;
import framework.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static framework.utils.Waiter.TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS;
import static framework.utils.Waiter.waitUntilExpected;

public class GoogleSearchResultPage extends BasePage {
    private static final String PAGE_URL = "https://www.google.com/search";

    private SelenideElement searchInput = $(By.xpath("//div[@id='searchform']//input[@name='q']"));

    @Override
    public void open() {
        throw new WebDriverException("Search result page should be opened by search input");
    }

    @Override
    public boolean isOpened() {
        return searchInput.isDisplayed();
    }

    @Override
    public void waitUntilPageOpened() {
        waitUntilExpected(webDriver ->
                getWebDriver().getCurrentUrl().startsWith(PAGE_URL), DEFAULT_TIMEOUT_5_000_MS);
        Waiter.waitUntilExpected(webDriver -> isOpened());
    }

    public String getInputValue() {
        return searchInput.getValue();
    }
}
