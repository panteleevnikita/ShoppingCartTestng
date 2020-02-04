package org.oneclicktestng.shopping;

import org.oneclicktesting.shopping.CartSummary;
import org.oneclicktesting.shopping.SeleniumCartSummary;
import org.oneclicktesting.shopping.SeleniumShop;
import org.oneclicktesting.shopping.Shop;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CartSummaryTest {
    CartSummary cartSummary;
    Shop shop;
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("Setting up headless chrome");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1200", "--ignore-certificate-errors");
        driver = new ChromeDriver(options);

        System.out.println("Setting up selenium services");
        this.cartSummary = new SeleniumCartSummary(driver);
        this.shop = new SeleniumShop(driver);
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Closing headless chrome");
        driver.quit();
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testEmptyCart() {
        int id = 1;
        cartSummary.getCartProductQuantity(id);
    }

    @Test
    public void testAddProductToCartAndRemove() {
        int quantity = 1;
        int id = 1;

        this.shop.addFeaturedProductToCart(id);
        Assert.assertEquals(cartSummary.getCartProductQuantity(id), quantity);
        this.cartSummary.removeProductFromCard(id);
    }

    @Test
    public void testAddSameProductToCartMultipleTimes() {
        int quantity = 3;
        int id = 1;

        for (int i = 0; i < quantity; i++) {
            this.shop.addFeaturedProductToCart(id);
        }
        Assert.assertEquals(cartSummary.getCartProductQuantity(id), quantity);
        this.cartSummary.removeProductFromCard(id);
    }
}
