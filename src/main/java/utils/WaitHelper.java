package utils;

import config.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {
    private static final int DEFAULT_TIMEOUT = 30;
    private static final ThreadLocal<WebDriverWait> waitThread = new ThreadLocal<>();

    private static WebDriverWait getWait() {
        if (waitThread.get() == null) {
            waitThread.set(new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT)));
        }
        return waitThread.get();
    }

    public static WebElement waitForElement(WebElement element) {
        try {
            return getWait().until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not visible after " + DEFAULT_TIMEOUT + " seconds: " + element);
        }
    }

    public static WebElement waitForElementToBeClickable(WebElement element) {
        try {
            // First ensure element is present and visible
            waitForElement(element);

            // Then wait for it to be clickable
            getWait().until(ExpectedConditions.and(
                ExpectedConditions.elementToBeClickable(element),
                driver -> {
                    // Check if element is not covered by other elements
                    return (Boolean) ((JavascriptExecutor) DriverManager.getDriver())
                        .executeScript(
                            "var elem = arguments[0];" +
                            "var rect = elem.getBoundingClientRect();" +
                            "var cx = rect.left + rect.width/2;" +
                            "var cy = rect.top + rect.height/2;" +
                            "return document.elementFromPoint(cx, cy) === elem;",
                            element
                        );
                }
            ));
            return element;
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not clickable after " + DEFAULT_TIMEOUT + " seconds: " + element);
        }
    }

    public static boolean waitForElementToDisappear(WebElement element) {
        try {
            return getWait().until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element still visible after " + DEFAULT_TIMEOUT + " seconds: " + element);
        }
    }

    public static void waitForUrlContains(String urlFragment) {
        try {
            getWait().until(ExpectedConditions.or(
                ExpectedConditions.urlContains(urlFragment),
                ExpectedConditions.urlContains(urlFragment.toLowerCase()),
                ExpectedConditions.urlContains(urlFragment.toUpperCase())
            ));
        } catch (TimeoutException e) {
            throw new TimeoutException("URL did not contain '" + urlFragment + "' after " + DEFAULT_TIMEOUT + " seconds");
        }
    }

    public static void waitForPageLoad() {
        getWait().until((ExpectedCondition<Boolean>) wd -> {
            try {
                String readyState = ((JavascriptExecutor) wd).executeScript("return document.readyState").toString();
                return readyState.equals("complete");
            } catch (WebDriverException e) {
                return false;
            }
        });
    }
}
