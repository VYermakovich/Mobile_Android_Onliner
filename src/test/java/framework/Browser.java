package framework;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;


public class Browser {

    private static final long IMPLICITLY_WAIT = 10;
    private static final String DEFAULT_CONDITION_TIMEOUT = "timeoutElement";

    static final String PROPERTIES_FILE = "application.properties";

    private static Browser instance;
    private static final MobileDriver<MobileElement> appiumDriver = DeviceFactory.getDriver();

    private static String timeoutForCondition;

    public static PropertyReader props;


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
        timeoutForCondition = PropertyReader.getProperty(DEFAULT_CONDITION_TIMEOUT);
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

    public MobileDriver<MobileElement> getDriver() {
        return appiumDriver;
    }
}