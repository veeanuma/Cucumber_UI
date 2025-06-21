package pages;

import config.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.StaleElementReferenceException;
import utils.WaitHelper;

public class LoginPage {
    @FindBy(id="forgotPassword")
    private WebElement forgotPassword;
    @FindBy(id="signupHere")
    private WebElement signupHere;
    @FindBy(id = "customerSupportEmail")
    private WebElement customerSupportEmail;
    @FindBy(id="loginPageTitle")
    private WebElement loginPageTitle;
    @FindBy(id="emailInput")@CacheLookup
    private WebElement email;
    @FindBy(id="passwordInput")@CacheLookup
    private WebElement password;
    @FindBy(id="loginButton")@CacheLookup
    private WebElement loginButton;
    @FindBy(xpath = "//div[text()='Incorrect username or password.']")
    private WebElement incorrectUsernameOrPassword;

    public LoginPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    private void refreshElements() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    private void retryingFindClick(WebElement element) {
        int attempts = 0;
        while(attempts < 2) {
            try {
                WaitHelper.waitForElementToBeClickable(element).click();
                break;
            } catch(StaleElementReferenceException e) {
                refreshElements();
            }
            attempts++;
        }
    }

    private void retryingSendKeys(WebElement element, String text) {
        int attempts = 0;
        while(attempts < 2) {
            try {
                WaitHelper.waitForElementToBeClickable(element);
                element.clear();
                element.sendKeys(text);
                break;
            } catch(StaleElementReferenceException e) {
                refreshElements();
            }
            attempts++;
        }
    }

    public void login(String username, String pwd) {
        // Wait for page to be fully loaded
        WaitHelper.waitForPageLoad();

        // Wait and enter username
        retryingSendKeys(email, username);

        // Wait and enter password
        retryingSendKeys(password, pwd);

        // Wait and click login button
        retryingFindClick(loginButton);
    }

    public void clickLoginButton() {
        retryingFindClick(loginButton);
    }
}
