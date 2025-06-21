package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm;
import io.qameta.allure.testng.AllureTestNg;

@Listeners({AllureTestNg.class})
@CucumberOptions(
    features = "src/test/resources/features/login.feature",
    glue = {"stepdefinitions"},
    plugin = {
        "html:target/cucumber-reports/cucumber-pretty.html",
        "json:target/cucumber-reports/CucumberTestReport.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "testng:target/cucumber-reports/testng-report.xml"
    },
    monochrome = true
)
public class Login_Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
