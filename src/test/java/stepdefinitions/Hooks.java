package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.TestContext;
import config.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.io.File;

public class Hooks {
    private final TestContext testContext;
    private static boolean reportPathPrinted = false;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setUp(Scenario scenario) {
        // DriverManager will initialize WebDriver when needed
        Allure.step("Starting scenario: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            // Attach screenshot to Allure report
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
        }
        Allure.step("Completing scenario: " + scenario.getName() + " with status: " + scenario.getStatus());
        testContext.tearDown();

        // Print Allure report path only once after all scenarios are complete
        if (!reportPathPrinted) {
            String projectPath = new File("").getAbsolutePath();
            String allureReportPath = projectPath + "\\target\\site\\allure-maven-plugin\\index.html";
            System.out.println("\nAllure Report Path: " + allureReportPath);
            reportPathPrinted = true;
        }
    }
}
