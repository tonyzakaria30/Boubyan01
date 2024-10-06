package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {

    private AppiumDriver<MobileElement> driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() throws MalformedURLException {
        // Set the desired capabilities for the Android app
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "10.0");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("app", "/path/to/your/app.apk");
        caps.setCapability("automationName", "UiAutomator2");

        // Initialize the Appium driver
        driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), caps);

        // Initialize the Page Object
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {
        loginPage.login("bob@example.com", "10203040");
        Assert.assertTrue(loginPage.isDashboardDisplayed(), "Dashboard should be displayed after valid login");
    }

    @Test
    public void invalidLoginTest() {
        loginPage.login("alice@example.com", "wrongPassword");
        Assert.assertFalse(loginPage.isDashboardDisplayed(), "Dashboard should not be displayed after invalid login");
    }

    @Test
    public void emptyFieldsLoginTest() {
        loginPage.login("", "");
        Assert.assertFalse(loginPage.isDashboardDisplayed(), "Dashboard should not be displayed after empty login details");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
