package framework;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Log4j2
public abstract class DeviceFactory implements MobileDriver<WebElement> {
    private static final DesiredCapabilities capabilities = new DesiredCapabilities();
    public static String platform = "android";

    public static URL url = null;
    public static MobileDriver<MobileElement> getDriver() {
        MobileDriver<MobileElement> appiumDriver = null;
        switch (platform) {
            case "android":
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
                capabilities.setCapability("udid", "emulator-5554");
                capabilities.setCapability("appActivity", ".screen.splash.SplashActivity");
                capabilities.setCapability("appPackage", "by.onliner.catalog");
                capabilities.setCapability("avd", "Android");
                try {
                    url = new URL("http://127.0.0.1:4723/wd/hub");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                appiumDriver = new AndroidDriver<>(url, capabilities);
                appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                break;
            case "ios":
                break;
        }
        return appiumDriver;
    }
}