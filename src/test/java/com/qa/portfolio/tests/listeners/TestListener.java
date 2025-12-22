package com.qa.portfolio.tests.listeners;

import com.qa.portfolio.driver.DriverFactory;
import com.qa.portfolio.utils.ScreenshotUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {
            byte[] screenshot = ScreenshotUtils.takeScreenshot(driver);

            // 🔥 BU SATIR OLMAZSA ALLURE BAZEN GÖSTERMEZ
            Allure.addAttachment(
                    "Failure Screenshot",
                    new ByteArrayInputStream(screenshot));
        }
    }
}
