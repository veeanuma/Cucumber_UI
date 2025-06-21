package stepdefinitions;

import config.DriverManager;
import config.WebDriverConfig;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.LoginPage;
import utils.WaitHelper;

public class LoginSteps {
    private final LoginPage loginPage;

    public LoginSteps() {
        loginPage = new LoginPage();
    }

    @Given("User is on login page")
    public void user_is_on_login_page() {
        DriverManager.getDriver().get(WebDriverConfig.getBaseUrl());
        WaitHelper.waitForPageLoad();
    }

    @When("User enters username and password")
    public void user_enters_username_and_password() {
        loginPage.login("jereji7472@doishy.com", "Sreenu80@");
    }

    @When("User clicks on login button")
    public void user_clicks_on_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        WaitHelper.waitForUrlContains("dashboard");
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("dashboard"),
            "User was not redirected to dashboard");
    }

    @Given("open browser")
    public void openBrowser() {
        // Driver is initialized automatically by DriverManager when needed
    }

    @When("User launch Login page")
    public void userLaunchLoginPage() {
        DriverManager.getDriver().get(WebDriverConfig.getBaseUrl());
        WaitHelper.waitForPageLoad();
    }

    @Then("User enters {string} and {string} valid credentials")
    public void userEntersValidCredentials(String username, String password) {
        loginPage.login(username, password);
    }

    @And("User is redirected to the Dashboard")
    public void userIsRedirectedToTheDashboard() {
        WaitHelper.waitForUrlContains("dashboard");
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("dashboard"),
            "User was not redirected to dashboard");
    }

    @And("User closes the browser")
    public void userClosesTheBrowser() {
        DriverManager.quitDriver();
    }
}
