package stepdefs;

import cucumber.api.java.en.Given;
import framework.context.BaseContext;
import framework.context.ContextKey;
import framework.pages.GoogleHomePage;
import framework.pages.GoogleSearchResultPage;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class GoogleStepDefinitions {
    private static final Logger logger = LoggerFactory.getLogger(GoogleStepDefinitions.class);
    private final GoogleHomePage googleHomePage = new GoogleHomePage();
    private final GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage();

    @Given("^I open google home page$")
    public void openHomePage() {
        googleHomePage.open();
        googleHomePage.waitUntilPageOpened();
    }

    @Given("^I search for random user facebook on google home page$")
    public void searchForRandomUserFacebook() {
        String firstName = (String) BaseContext.getValue(ContextKey.API_RANDOM_USER_FIRST_NAME);
        String lastName = (String) BaseContext.getValue(ContextKey.API_RANDOM_USER_LAST_NAME);
        String key = String.format("%s %s facebook", firstName, lastName);
        googleHomePage.searchFor(key);
        googleSearchResultPage.waitUntilPageOpened();
        String actualInputValue = googleSearchResultPage.getInputValue();
        assertEquals(String.format("Google search input value '%s' is not as expected on Google SRP page. Expected state is '%s'",
                actualInputValue, key), actualInputValue, key);
    }
}
