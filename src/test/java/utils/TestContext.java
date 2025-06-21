package utils;

import config.DriverManager;

public class TestContext {
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
