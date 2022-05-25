package framework;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(TestListener.class)
@Log4j2
public class BaseTest {

    public static Browser browser;
    protected ITestContext context;

    @BeforeMethod
    public void before(ITestContext context) {
        this.context = context;
        browser = Browser.getInstance();
        browser.get(PropertyReader.getProperty("URL"));
        context.setAttribute("driver", browser);
        log.info("Site has been opened: URL - " + PropertyReader.getProperty("URL"));
    }

    @AfterTest(alwaysRun = true, description = "Closing browser")
    public void after() {
        browser.exit();
    }
}