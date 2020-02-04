package org.oneclicktestng.shopping;

import org.oneclicktesting.shopping.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CartTest {
    Cart cart;
    Shop shop;
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("Setting up headless chrome");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1200", "--ignore-certificate-errors");
        driver = new ChromeDriver(options);

        System.out.println("Setting up selenium services");
        this.cart = new SeleniumCart(driver);
        this.shop = new SeleniumShop(driver);
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Closing headless chrome");
        driver.quit();
    }

    @Test
    public void testAddProductToCartAndRemove() {
        int quantity = 1;
        int id = 1;

        shop.addFeaturedProductToCart(id);
        int cartQuantity = cart.getCartProductQuantity(id);

        Assert.assertEquals(cartQuantity, quantity);
        cart.removeProductFromCard(id);
    }

}
