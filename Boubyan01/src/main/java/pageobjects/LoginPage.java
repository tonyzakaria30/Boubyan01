package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private AppiumDriver driver;


    @FindBy(id = "username_input_id")
    private MobileElement usernameField;

    @FindBy(id = "password_input_id")
    private MobileElement passwordField;

    @FindBy(id = "login_button_id")
    private MobileElement loginButton;

    @FindBy(id = "dashboard_element_id")
    private MobileElement dashboard;

    // Constructor
    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    // Login method
    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    // Check if dashboard is displayed
    public boolean isDashboardDisplayed() {
        return dashboard.isDisplayed();
    }
}
