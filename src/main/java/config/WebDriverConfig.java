package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverConfig {
    private static final Properties props = loadProperties();

    private WebDriverConfig() {
        // Private constructor to prevent instantiation
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
        return properties;
    }

    public static WebDriver createDriver() {
        try {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--dns-prefetch-disable");
           // options.addArguments("--headless"); // Run in headless mode for better stability
            options.setExperimentalOption("useAutomationExtension", false);

            return new ChromeDriver(options);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize ChromeDriver: " + e.getMessage(), e);
        }
    }

    public static String getBaseUrl() {
        return props.getProperty("baseUrl");
    }
}
