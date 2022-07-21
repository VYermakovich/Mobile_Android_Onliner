package framework;

import framework.elements.Label;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Date;

@Log4j2
abstract public class BaseForm {

    protected String title;
    protected By titleLocator;

    public BaseForm(final By locator, final String pageTitle) {
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
            elem.isElementPresent();
            long openTime = new Date().getTime() - before;
            log.info(String.format(PropertyReader.getProperty("loc.form.appears"), title) + String.format(" in %s msec", openTime));

        } catch (Throwable e) {
            Assert.assertTrue(true, title + " does not open");
        }
    }
}