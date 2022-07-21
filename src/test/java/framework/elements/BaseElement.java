package framework.elements;

import framework.BaseTest;
import framework.PropertyReader;
import io.appium.java_client.MobileElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;


@Log4j2
public abstract class BaseElement extends BaseTest {
    private static final PropertyReader propertyReader = new PropertyReader("log.properties");
    protected MobileElement element;
    private final By by;

    public BaseElement(By by) {
        this.by = by;
        getElementType();
    }

    public BaseElement(By by, String name) {
        this.by = by;
        getElementType();
    }

    protected abstract String getElementType();

    public static String getLoc(final String key) {
        return propertyReader.getProperty(key);
    }


    public MobileElement isElementPresent() {
        try {
            browser.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(browser.getTimeoutForCondition()), TimeUnit.SECONDS);
            element = browser.getDriver().findElement(by);
            log.info(getElementType() +": " + by + " - is present");
            element.isDisplayed();
            return element;
        } catch (Exception e) {
            log.error(getElementType() + ": " + by + " - is not present. Exception - " + e);
            e.printStackTrace();
        }
        return element;
    }

    public void click() {
        isElementPresent();
        element.click();
        log.info(PropertyReader.getProperty("log.click") + getElementType() + ": " + by);
    }
}