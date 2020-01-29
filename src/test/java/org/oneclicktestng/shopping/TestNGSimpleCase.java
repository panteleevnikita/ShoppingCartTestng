package org.oneclicktestng.shopping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNGSimpleCase {
    public String baseUrl = "http://automationpractice.com/";
    public WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.out.println("Setting up headless chrome");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
        driver = new ChromeDriver(options);
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Closing headless chrome");
        driver.quit();
    }

    @Test
    public void verifyHomepageTitle() {
        driver.get(baseUrl);

        String expectedTitle = "My Store";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

        driver.close();
    }
}
