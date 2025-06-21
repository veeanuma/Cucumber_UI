package config;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.ConcurrentHashMap;

public class DriverManager {
    private static final ConcurrentHashMap<Long, WebDriver> driverMap = new ConcurrentHashMap<>();

    private DriverManager() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getDriver() {
        Long threadId = Thread.currentThread().getId();
        if (!driverMap.containsKey(threadId)) {
            WebDriver driver = WebDriverConfig.createDriver();
            driverMap.put(threadId, driver);
        }
        return driverMap.get(threadId);
    }

    public static void quitDriver() {
        Long threadId = Thread.currentThread().getId();
        WebDriver driver = driverMap.get(threadId);
        if (driver != null) {
            driver.quit();
            driverMap.remove(threadId);
        }
    }
}
