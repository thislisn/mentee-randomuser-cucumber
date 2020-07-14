package framework.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import framework.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class GoogleHomePage extends BasePage {
    private static final String PAGE_URL = "https://www.google.com/";

    private SelenideElement searchInput = $(By.xpath("//div[@id='searchform']//input[@name='q']"));

    @Override
    public void open() {
        Selenide.open(PAGE_URL);
    }

    @Override
    public boolean isOpened() {
        return searchInput.isDisplayed();
    }

    @Override
    public void waitUntilPageOpened() {
        waiForUrl(PAGE_URL);
        Waiter.waitUntilExpected(webDriver -> isOpened());
    }

    public void searchFor(String key){
        searchInput.sendKeys(key);
        searchInput.sendKeys(Keys.ENTER);
    }
}
