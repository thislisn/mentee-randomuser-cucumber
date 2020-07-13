package stepdefs;

import com.codeborne.selenide.Configuration;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import framework.context.BaseContext;
import framework.context.ContextKey;

import static framework.properies.PropertyReader.getBrowserType;

public class Hooks {

    @Before
    public void before() {
        String browserType = getBrowserType();
        BaseContext.setValue(ContextKey.BROWSER_TYPE, browserType);
        Configuration.browser = browserType;
    }

    @After
    public void after() {
    }

}
