package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public final class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
        driver = null;
    }
}

