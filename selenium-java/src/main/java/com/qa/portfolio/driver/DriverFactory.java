package com.qa.portfolio.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * DriverFactory is responsible for creating and managing the WebDriver
 * instance.
 * It configures Chrome to run in headless mode for CI environments and provides
 * utility methods to stabilize tests (e.g., waiting for elements).
 */
public class DriverFactory {
    private static WebDriver driver;

    /**
     * Initialize the ChromeDriver with recommended options for CI stability.
     * - headless: run without opening a browser window
     * - no-sandbox / disable-dev-shm-usage: prevent resource issues in CI
     */
    public static WebDriver initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless=new"); // CI-friendly headless mode

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Alias for backward compatibility with existing tests.
     * Calls initializeDriver() internally.
     */
    public static WebDriver initDriver() {
        return initializeDriver();
    }

    /**
     * Get the current WebDriver instance.
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Quit the WebDriver and clean up resources.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Wait until the given element is visible on the page.
     * This replaces Thread.sleep and makes tests more reliable.
     *
     * @param locator        The By locator of the element
     * @param timeoutSeconds Maximum wait time in seconds
     * @return WebElement once it becomes visible
     */
    public static WebElement waitForElement(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}