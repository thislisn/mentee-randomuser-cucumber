package framework.browser;

import framework.context.BaseContext;
import framework.context.ContextKey;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public class DriverProvider {
    private static final Logger logger = LoggerFactory.getLogger(DriverProvider.class);
    private static ThreadLocal<WebDriver> driverInstance = new ThreadLocal<>();

    private static void setup() {
        String browserType = (String) BaseContext.getValue(ContextKey.BROWSER_TYPE);
        switch (browserType) {
            case CHROME: {
                WebDriverManager.chromedriver().setup();
                setDriver(new ChromeDriver());
                break;
            }
            case FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                setDriver(new FirefoxDriver());
                break;
            }
            default: {
                throw new RuntimeException("Browser " + browserType + " is not supported");
            }
        }
    }

    public static void setDriver(WebDriver driver){
        logger.info("Setting the driver");
        driverInstance.set(driver);
    }

    public static WebDriver getDriver() {
        logger.info("Getting instance of remote driver");
        if (driverInstance.get() == null) {
            setup();
        }
        return driverInstance.get();
    }

    public static void closeDriver() {
        logger.info("Closing instance of remote driver");
        if (!(driverInstance.get() == null)) {
            getDriver().close();
        }
    }
}
