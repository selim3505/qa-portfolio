package com.qa.portfolio.tests.smoke;

import com.qa.portfolio.driver.DriverFactory;
import com.qa.portfolio.utils.ConfigReader;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * SmokeTest verifies that the application base URL is accessible.
 * This acts as a quick health check before running deeper tests.
 */
@Epic("SauceDemo Tests")
@Feature("Smoke Feature")
public class SmokeTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        DriverFactory.quitDriver();
    }

    @Test
    @Description("Verify that the base URL loads successfully")
    public void baseUrlLoadsTest() {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Swag Labs"), "Base URL did not load correctly!");
    }
}