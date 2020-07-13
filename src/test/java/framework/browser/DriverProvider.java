package framework.browser;

import framework.context.BaseContext;
import framework.context.ContextKey;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static framework.properies.PropertyReader.getBrowserType;

public class DriverProvider {
    private static final Logger logger = LoggerFactory.getLogger(DriverProvider.class);
    private static ThreadLocal<WebDriver> driverInstance = new ThreadLocal<>();
    private DriverFactory driverFactory;

    public static WebDriver getInstance() {
        String browserType = getBrowserType();
        BaseContext.setValue(ContextKey.BROWSER_TYPE, browserType);
        if (driverInstance.get() == null) {
//            driverInstance.set(DriverFactory.getDriver());
            WebDriverManager.chromedriver().setup();
            driverInstance.set(new ChromeDriver());
        }
        return driverInstance.get();
    }
}
