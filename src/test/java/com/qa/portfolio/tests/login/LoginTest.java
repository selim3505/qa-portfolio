package com.qa.portfolio.tests.login;

import com.qa.portfolio.driver.DriverFactory;
import com.qa.portfolio.pages.LoginPage;
import com.qa.portfolio.utils.ConfigReader;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * LoginTest demonstrates positive and negative login scenarios
 * using Selenium WebDriver, TestNG, and Allure reporting.
 * Screenshots are captured automatically on failure via TestListener.
 */
@Epic("SauceDemo Tests")
@Feature("Login Feature")
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
    }
    
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

   
    @Test
    @Story("Valid Login")
    @Description("Login with valid credentials should redirect to inventory page")
    public void validLoginTest() {
        loginPage.login("standard_user", "secret_sauce");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "Login failed!");
    }

    @Test
    @Story("Invalid Login")
    @Description("Login with invalid credentials should display an error message")
    public void invalidLoginTest() {
        loginPage.login("wrong_user", "wrong_pass");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Epic sadface"), "Error message did not match expected!");
    }

    @Test
    @Story("Forced Failure")
    @Description("This test is intentionally designed to fail for demonstration purposes")
    public void forcedFailureTest() {
        // Browser ve login işlemini tamamla, sonra fail ol
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(false, "This test is supposed to fail!");
    }
}