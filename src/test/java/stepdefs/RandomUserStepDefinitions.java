package stepdefs;

import cucumber.api.java.en.Given;
import framework.api.RandomUserApi;
import framework.context.BaseContext;
import framework.context.ContextKey;
import framework.dto.Name;
import framework.dto.RandomUserNameDto;

public class RandomUserStepDefinitions {
    private final RandomUserApi randomUserApi = new RandomUserApi();

    @Given("get random user name")
    public void getRandomUserName() {
        RandomUserNameDto randomUserNameDto = randomUserApi.getRandomUserName();
        Name name = randomUserNameDto.getResults().get(0).getName();
        BaseContext.setValue(ContextKey.API_RANDOM_USER_FIRST_NAME, name.getFirst());
        BaseContext.setValue(ContextKey.API_RANDOM_USER_LAST_NAME, name.getLast());
    }


}
