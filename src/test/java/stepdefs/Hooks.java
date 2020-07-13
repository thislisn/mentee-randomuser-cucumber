package stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import framework.browser.DriverProvider;
import framework.context.BaseContext;
import framework.context.ContextKey;
import org.openqa.selenium.WebDriver;

import static framework.properies.PropertyReader.getBrowserType;

public class Hooks {
    WebDriver webDriver;

    @Before
    public void initializeTest() throws Exception {
//        Initializer.getInstance().init();
//        DriverProvider.getInstance();
        System.out.println("Before hook");
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        System.out.println("After hook");
//        DriverProvider.getInstance().close();
        if (scenario.isFailed()) {
//            try {
//                System.out.println("Test finished with error");
//                driver.quit();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            System.out.println("action for failed scenario ...");
        }
    }


}
