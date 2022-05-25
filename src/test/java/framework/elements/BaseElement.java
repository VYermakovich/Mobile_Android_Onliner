package framework.elements;

import framework.BaseTest;
import framework.Browser;
import framework.PropertyReader;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static framework.PropertyReader.getProperty;

@Log4j2
public abstract class BaseElement extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    PropertyReader propertyReader = new PropertyReader("log.properties");
    protected WebElement element;
    protected List<WebElement> elements;
    private By by;
    private String name;
    private WebDriverWait wait;
    private int invalidImageCount;

    public static String getLoc(final String key) {
        return getProperty(key);
    }

    public WebElement getElement() {
        waitForIsElementPresent();
        return element;
    }

    public BaseElement(By by) {
        this.by = by;
    }

    public BaseElement(By by, String name) {
        this.by = by;
        this.name = name;
    }

    protected abstract String getElementType();

    public boolean waitForIsElementPresent() {
        isElementPresent(Integer.parseInt(browser.getTimeoutForCondition()));
        return true;
    }

    public List<WebElement> getElements() {
        waitForElementsArePresent();
        return elements;
    }

    public WebElement getAttribute(String attribute) {
        element.getAttribute(attribute);
        return element;
    }

    public void waitForElementsArePresent() {
        areElementsPresent(Integer.parseInt(browser.getTimeoutForCondition()));
    }

    public boolean isElementPresent(int timeout) {
        wait = new WebDriverWait(browser.getDriver(), timeout);
        try {
            browser.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(browser.getTimeoutForCondition()), TimeUnit.SECONDS);
            element = browser.getDriver().findElement(by);
            log.info(getElementType() + ": " + by + " - is present");
            return element.isDisplayed();
        } catch (Exception e) {
            log.error(getElementType() + ": " + by + " - is not present. Exception - " + e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean areElementsPresent(int timeout) {
        wait = new WebDriverWait(Browser.getInstance().getDriver(), timeout);
        browser.getDriver().manage().timeouts().implicitlyWait(Integer.valueOf(browser.getTimeoutForCondition()), TimeUnit.SECONDS);
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver driver) {
                    try {
                        elements = driver.findElements(by);
                        for (WebElement element : elements) {
                            if (element instanceof WebElement && element.isDisplayed()) {
                                element = (WebElement) element;
                                return element.isDisplayed();
                            }
                        }
                        element = (WebElement) driver.findElement(by);
                    } catch (Exception e) {
                        return false;
                    }
                    return element.isDisplayed();
                }
            });
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void sendKeys(String sendKeys) {
        getElement().sendKeys(sendKeys);
    }

    public boolean isSelected() {
        waitForIsElementPresent();
        log.info(getProperty("log.select") + element.getText());
        return element.isSelected();
    }

    public boolean isDisplayed() {
        waitForIsElementPresent();
       if (element.isDisplayed()){
           log.info(by + " is displayed");
           return true;
       }else {
           log.info(by + " is not displayed");
           return false;
       }
    }

    public void click() {
        waitForIsElementPresent();
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid blue'", element);
            element.click();
        }
        log.info(getProperty("log.click") + ": " + getElementType() + ": " + by);
    }

    public void clickAndWait() {
        waitForIsElementPresent();
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
            element.click();
        }
        browser.waitForPageToLoad();
        log.info(getProperty("log.click") + ": " + getElementType() + " = " + by + ". And waits for the page to load ");
    }

    public void clickViaJS() {
        waitForIsElementPresent();
        if (browser.getDriver() instanceof JavascriptExecutor) {
            Actions actions = new Actions(Browser.getInstance().getDriver());
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
            actions.moveToElement(element).build().perform();
        }
    }

    public String getText() {
        waitForIsElementPresent();
        log.info(getLoc("log.get.text") + ": " + element.getText());
        return element.getText();
    }

    public void moveAndClickByAction() {
        waitForIsElementPresent();
        Actions actions = new Actions(Browser.getInstance().getDriver());
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid blue'", element);
            actions.moveToElement(element).perform();
            actions.click().perform();
            log.info(getProperty("log.moveAndClick") + ": " + getElementType() + " = " + by + ". And waits for the page to load ");
        }
        browser.waitForPageToLoad();
    }

    public void moveToElement() {
        waitForIsElementPresent();
        Actions actions = new Actions(Browser.getInstance().getDriver());
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid blue'", element);
            actions.moveToElement(element).perform();
        }
        log.info(getLoc(PropertyReader.getProperty("log.moveTo") + ":" + getElementType() + "=" + by));
    }

    public void selectComboBox(String value) {
        waitForIsElementPresent();
        Select select = new Select(element);
        select.selectByVisibleText(value);
        log.info(getLoc("log.select") + ": " + value);
    }

    public void scrollIntoView(){
        waitForIsElementPresent();
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid blue'", element);
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);
        }
    }

    public void findBrokenImg() {
        try {
            invalidImageCount = 0;
            List<WebElement> imagesList = getElements();
            log.info("Total number of images are " + imagesList.size());
            for (WebElement imgElement : imagesList) {
                if (imgElement != null) {
                    verifyImageActive(imgElement);
                }
            }
            log.info("Total number of invalid images are - " + invalidImageCount);
            softAssert.assertAll();
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage() + e);
        }
        softAssert.assertAll();
    }

    public void verifyImageActive(WebElement imgElement) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(imgElement.getAttribute("src"));
            HttpResponse response = client.execute(request);
            softAssert.assertEquals(response.getStatusLine().getStatusCode(), 200, request.toString() + " - is broken");
            if (response.getStatusLine().getStatusCode() != 200)
                invalidImageCount++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract String[] split(String s);

    public Point getLocationOfElement(){
        Point x = element.getLocation();
        log.info("Location of element - " + x);
        return x;
    }

    public abstract int size();
}