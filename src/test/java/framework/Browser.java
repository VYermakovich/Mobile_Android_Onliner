package framework;

import io.appium.java_client.MobileDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

@Log4j2
public class Browser {

    private static final long IMPLICITLY_WAIT = 10;
    private static final String DEFAULT_CONDITION_TIMEOUT = "timeoutElement";
    private static final String DEFAULT_PAGE_LOAD_TIMEOUT = "timeout";

    static final String PROPERTIES_FILE = "conf.properties";

    private static Browser instance;
    private static MobileDriver<WebElement> appiumDriver = DeviceFactory.getDriver();

    private static String timeoutForPageLoad;
    private static String timeoutForCondition;

    public static PropertyReader props;

    public boolean isBrowserAlive() {
        return instance != null;
    }


    public static Browser getInstance() {
        if (instance == null) {
            initProperties();
            try {
                appiumDriver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
            } catch (Exception e) {
                Assert.fail("Driver does not instance");
            }
            instance = new Browser();
        }
        return instance;
    }

    private static void initProperties() {
        props = new PropertyReader(PROPERTIES_FILE);
        timeoutForPageLoad = props.getProperty(DEFAULT_PAGE_LOAD_TIMEOUT);
        timeoutForCondition = props.getProperty(DEFAULT_CONDITION_TIMEOUT);
    }

    public void exit() {
        try {
            appiumDriver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            instance = null;
        }
    }

    public String getTimeoutForCondition() {
        return timeoutForCondition;
    }

    public String getTimeoutForPageLoad() {
        return timeoutForPageLoad;
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, Long.parseLong(getTimeoutForPageLoad()));
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver d) {
                    if (!(d instanceof JavascriptExecutor)) {
                        return true;
                    }
                    Object result = ((JavascriptExecutor) d)
                            .executeScript("return document['readyState'] ? 'complete' === document.readyState : true");
                    return result instanceof Boolean && (Boolean) result;
                }
            });
        } catch (Exception e) {
            Assert.fail("Page does not Load" + e);
        }
    }

    public void get(String url) {
        appiumDriver.get(url);
    }

    public MobileDriver<WebElement> getDriver() {
        return appiumDriver;
    }

    public String getLocation() {
        return appiumDriver.getCurrentUrl();
    }
}