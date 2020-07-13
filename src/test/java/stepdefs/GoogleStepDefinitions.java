package stepdefs;

import cucumber.api.java.en.Given;
import framework.api.RandomUserApi;
import framework.context.BaseContext;
import framework.context.ContextKey;
import framework.dto.Name;
import framework.dto.RandomUserNameDto;
import framework.pages.GooglePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleStepDefinitions {
    private static final Logger logger = LoggerFactory.getLogger(GoogleStepDefinitions.class);
    private final GooglePage googlePage = new GooglePage();

    @Given("^I open google page$")
    public void openHomePage() {
        googlePage.open();
    }
}
