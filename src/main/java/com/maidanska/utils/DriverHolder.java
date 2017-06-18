package com.maidanska.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverHolder {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            final String driverPath = "src/main/resources/chromedriver";
            System.setProperty("webdriver.chrome.driver", driverPath);
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            return driver;
        }
    };

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void removeDriver() {
        driver.get().quit();
        driver.remove();
    }
}
