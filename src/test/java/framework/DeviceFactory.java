package framework;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public abstract class DeviceFactory implements MobileDriver<WebElement> {

    private static MobileDriver<WebElement> appiumDriver;


    public static MobileDriver<WebElement> getDriver() {
        JsonObject jsonObject = null;
        JsonParser parser = new JsonParser();
        URL url = null;
        String platform = "android";
        switch (platform) {
            case "android":
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
                capabilities.setCapability("udid", "emulator-5554");
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("excludeSwitches",
                        Arrays.asList("disable-popup-blocking"));
                try {
                    url = new URL("http://127.0.0.1:4723/wd/hub");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                appiumDriver = new AndroidDriver<WebElement>(url, capabilities);
                break;
            case "iphone":
                // TO DO LATER
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + platform);
        }
        appiumDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return appiumDriver;
    }
}