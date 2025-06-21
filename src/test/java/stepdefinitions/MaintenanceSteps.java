package stepdefinitions;

import io.cucumber.java.en.*;
import pages.MaintenancePage;
import org.testng.Assert;

public class MaintenanceSteps {
    private final MaintenancePage maintenancePage;

    public MaintenanceSteps() {
        maintenancePage = new MaintenancePage();
    }

    @Given("User clicks on Add New button")
    public void userClicksOnAddNewButton() {
        maintenancePage.clickAddNew();
    }

    @When("User selects portfolio {string}")
    public void userSelectsPortfolio(String portfolio) {
        maintenancePage.selectPortfolio(portfolio);
    }

    @When("User selects property {string}")
    public void userSelectsProperty(String property) {
        maintenancePage.selectProperty(property);
    }

    @When("User clicks confirm button")
    public void userClicksConfirmButton() {
        maintenancePage.clickConfirm();
    }

    @When("User selects request type {string}")
    public void userSelectsRequestType(String requestType) {
        maintenancePage.selectRequestType(requestType);
    }

    @When("User enters problem description {string}")
    public void userEntersProblemDescription(String description) {
        maintenancePage.enterProblemDescription(description);
    }

    @When("User clicks next button")
    public void userClicksNextButton() {
        maintenancePage.clickNext();
    }

    @When("User selects category {string}")
    public void userSelectsCategory(String category) {
        maintenancePage.selectCategory(category);
    }

    @When("User selects subcategory {string}")
    public void userSelectsSubcategory(String subcategory) {
        maintenancePage.selectSubCategory(subcategory);
    }

    @When("User enters location {string}")
    public void userEntersLocation(String location) {
        maintenancePage.enterLocation(location);
    }

    @When("User selects priority {string}")
    public void userSelectsPriority(String priority) {
        maintenancePage.selectPriority(priority);
    }

    @When("User selects assignee {string}")
    public void userSelectsAssignee(String assignee) {
        maintenancePage.selectAssignee(assignee);
    }

    @When("User uploads media file {string}")
    public void userUploadsMediaFile(String fileName) {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testdata/" + fileName;
        maintenancePage.uploadMedia(filePath);
    }

    @When("User enters media notes {string}")
    public void userEntersMediaNotes(String notes) {
        maintenancePage.enterMediaNotes(notes);
    }

    @When("User selects vendor {string}")
    public void userSelectsVendor(String vendor) {
        maintenancePage.selectVendor(vendor);
    }

    @When("User sets first availability date and time")
    public void userSetsFirstAvailabilityDateTime() {
        maintenancePage.setAvailability1("2025-06-22", "09:00", "12:00");
    }

    @When("User sets second availability date and time")
    public void userSetsSecondAvailabilityDateTime() {
        maintenancePage.setAvailability2("2025-06-23", "14:00", "17:00");
    }

    @When("User clicks create request button")
    public void userClicksCreateRequestButton() {
        maintenancePage.clickCreateRequest();
    }

    @Then("Maintenance request should be created successfully")
    public void maintenanceRequestShouldBeCreatedSuccessfully() {
        maintenancePage.waitForLoaderToDisappear();
        Assert.assertTrue(maintenancePage.isSuccessMessageDisplayed(), "Success message was not displayed");
    }

    @Given("User clicks on maintenance button")
    public void userClicksOnMaintenanceButton() {
        maintenancePage.clickMaintenanceButton();
    }
}
