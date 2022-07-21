package framework;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.annotations.*;

import static framework.appium.AppiumServer.startServer;
import static framework.appium.AppiumServer.stopServer;

@Listeners(TestListener.class)
@Log4j2
public class BaseTest {
    public static Browser browser;
    protected ITestContext context;

    @BeforeSuite (alwaysRun = true)
    protected void startAppiumServer() {
        log.info("@BeforeSuite - startAppiumServer()");
        startServer();
    }

    @BeforeMethod
    public void setup(ITestContext context) {
        this.context = context;
        browser = Browser.getInstance();
    }

    @AfterMethod
    public void tearDown() {
        browser.exit();
    }

    @AfterSuite (alwaysRun = true)
    public void stop() {
        log.info("@AfterSuite - stopAppiumServer()");
        stopServer();
    }
}