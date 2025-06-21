package pages;

import config.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.StaleElementReferenceException;
import utils.WaitHelper;
import java.util.List;

public class MaintenancePage {
    @FindBy(id="addNew")
    private WebElement addNew;
   /* @FindBy(id="maintenance")
    private WebElement maintenance;*/
    @FindBy(id="portfolioIdSelect")
    private WebElement portfolioIdSelect;
    @FindBy(id="propertySelect")
    private WebElement propertySelect;
    @FindBy(xpath="//button[text()='Confirm']")
    private WebElement confirmButton;
    @FindBy(id="requestTypeSelect")
    private WebElement requestTypeSelect;
    @FindBy(id="problemDescriptionInput")
    private WebElement problemDescriptionInput;
    @FindBy(xpath="//button[text()='Next']")
    private WebElement nextButton;
    @FindBy(id="categorySelect")
    private WebElement categorySelect;
    @FindBy(id="SubCategorySelect")
    private WebElement SubCategorySelect;
    @FindBy(id="locationInput")
    private WebElement locationInput;
    @FindBy(id="prioritySelect")
    private WebElement prioritySelect;
    @FindBy(id="assigntoSelect")
    private WebElement assignToSelect;
    @FindBy(xpath="//input[@type='file']")
    private WebElement mediaSelectFileButton;
    @FindBy(id="mediaNotesInput")
    private WebElement mediaNotesInput;
    @FindBy(id="vendorSelect")
    private WebElement vendorSelect;
    @FindBy(id="availability1DatePicker")
    private WebElement availability1DatePicker;
    @FindBy(id="availability2DatePicker")
    private WebElement availability2DatePicker;
    @FindBy(id="availability1FromTimeInput")
    private WebElement availability1FromTimeInput;
    @FindBy(id="availability1ToTimeInput")
    private WebElement availability1ToTimeInput;
    @FindBy(id="availability2FromTimeInput")
    private WebElement availability2FromTimeInput;
    @FindBy(id="availability2ToTimeInput")
    private WebElement availability2ToTimeInput;
    @FindBy(id="createRequest")
    private WebElement createRequest;
    @FindBy(xpath="(//span[@role='progressbar']//parent::div)[1]")
    private WebElement Loader;
    @FindBy(id="sucessMessage")
    private WebElement sucessMessage;
    @FindBy(id="close")
    private WebElement close;
    @FindBy(xpath="//div[starts-with(@class,'MuiClock-pin')]")
    private List<WebElement> ClockPin;
    @FindBy(xpath="//span[@role='option']")
    private List<WebElement> HoursList;
    @FindBy(xpath="(//div[starts-with(@class,'MuiDialogActions')]//button)[2]")
    private WebElement OK_Button;
    @FindBy(xpath="(//button[@aria-label='Choose date'])[1]")
    private WebElement Date1;
    @FindBy(xpath="(//button[@aria-label='Choose date'])[2]")
    private WebElement Date2;
    @FindBy(xpath="(//div[starts-with(@class,'MuiPickersCalendarHeader')])[3]")
    private WebElement CalendarHeader;
    @FindBy(css = "a[id='maintenance'], a[href='/maintenance']")
    private WebElement maintenanceButton;

    public MaintenancePage() {
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

    public void clickAddNew() {
        retryingFindClick(addNew);
    }
    /*public void clickMaintenance() {
        retryingFindClick(maintenance);
    }*/

    public void selectPortfolio(String portfolio) {
        retryingSendKeys(portfolioIdSelect, portfolio);
    }

    public void selectProperty(String property) {
        retryingSendKeys(propertySelect, property);
    }

    public void clickConfirm() {
        retryingFindClick(confirmButton);
    }

    public void selectRequestType(String requestType) {
        retryingSendKeys(requestTypeSelect, requestType);
    }

    public void enterProblemDescription(String description) {
        retryingSendKeys(problemDescriptionInput, description);
    }

    public void clickNext() {
        retryingFindClick(nextButton);
    }

    public void selectCategory(String category) {
        retryingSendKeys(categorySelect, category);
    }

    public void selectSubCategory(String subCategory) {
        retryingSendKeys(SubCategorySelect, subCategory);
    }

    public void enterLocation(String location) {
        retryingSendKeys(locationInput, location);
    }

    public void selectPriority(String priority) {
        retryingSendKeys(prioritySelect, priority);
    }

    public void selectAssignee(String assignee) {
        retryingSendKeys(assignToSelect, assignee);
    }

    public void uploadMedia(String filePath) {
        mediaSelectFileButton.sendKeys(filePath);
    }

    public void enterMediaNotes(String notes) {
        retryingSendKeys(mediaNotesInput, notes);
    }

    public void selectVendor(String vendor) {
        retryingSendKeys(vendorSelect, vendor);
    }

    public void setAvailability1(String date, String fromTime, String toTime) {
        WaitHelper.waitForElementToBeClickable(availability1DatePicker).sendKeys(date);
        availability1FromTimeInput.sendKeys(fromTime);
        availability1ToTimeInput.sendKeys(toTime);
    }

    public void setAvailability2(String date, String fromTime, String toTime) {
        WaitHelper.waitForElementToBeClickable(availability2DatePicker).sendKeys(date);
        availability2FromTimeInput.sendKeys(fromTime);
        availability2ToTimeInput.sendKeys(toTime);
    }

    public void clickCreateRequest() {
        retryingFindClick(createRequest);
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            WaitHelper.waitForElement(sucessMessage);
            return sucessMessage.isDisplayed();
        } catch(StaleElementReferenceException e) {
            refreshElements();
            WaitHelper.waitForElement(sucessMessage);
            return sucessMessage.isDisplayed();
        }
    }

    public void waitForLoaderToDisappear() {
        try {
            WaitHelper.waitForElementToDisappear(Loader);
        } catch(StaleElementReferenceException e) {
            refreshElements();
            WaitHelper.waitForElementToDisappear(Loader);
        }
    }

    public void clickMaintenanceButton() {
        WaitHelper.waitForPageLoad();
        try {
            Thread.sleep(1000); // Small wait for page stability after login
            // Try JavaScript click if normal click fails
            try {
                retryingFindClick(maintenanceButton);
            } catch (Exception e) {
                ((org.openqa.selenium.JavascriptExecutor) DriverManager.getDriver())
                    .executeScript("arguments[0].click();", maintenanceButton);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
