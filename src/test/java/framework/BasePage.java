package framework;

import framework.elements.Label;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Date;


@Log4j2
public class BasePage extends BaseTest {
    protected String title;
    protected By titleLocator;
    protected PropertyReader propertyReader = new PropertyReader("log.properties");


    public BasePage(final By locator, final String pageTitle) {
        init(locator, pageTitle);
        assertIsOpen();
    }

    private void init(final By locator, final String pageTitle) {
        titleLocator = locator;
        title = pageTitle;
    }

    public void assertIsOpen() {
        long before = new Date().getTime();
        Label elem = new Label(titleLocator, title);
        try {
            elem.waitForIsElementPresent();
            long openTime = new Date().getTime() - before;
            log.info(String.format(propertyReader.getProperty("loc.form.appears"), title) + String.format(" in %s msec", openTime));

        } catch (Throwable e) {
            Assert.assertTrue(true, title + " does not open");
        }
    }
}