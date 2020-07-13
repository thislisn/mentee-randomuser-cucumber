package framework.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GooglePage extends BasePage {
    private static final String PAGE_URL = "https://www.google.com";

    private SelenideElement searchInput = $(By.cssSelector("input#searchInput"));

    @Override
    public void open() {
        Selenide.open(PAGE_URL);
    }

    @Override
    public boolean isOpened() {
        return false;
    }

    @Override
    public void waitUntilPageOpened() {

    }
}
