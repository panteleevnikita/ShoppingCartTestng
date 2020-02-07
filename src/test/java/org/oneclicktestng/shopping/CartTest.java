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
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
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

    @Test
    public void testTotalPriceOfMultipleProducts() {
        int totalQuantity = 3;

        float totalPrice = 0;

        for (int i = 1; i <= totalQuantity; i++) {
            this.shop.addFeaturedProductToCart(i);
            totalPrice += this.shop.getProductPrice(i);
        }

        Assert.assertEquals(cart.getCartTotalPrice(), totalPrice);

        for (int i = 0; i < totalQuantity; i++) {
            this.cart.removeProductFromCard(1);
        }
    }

    @Test
    public void testProductPrice() {
        int id = 3;
        this.shop.addFeaturedProductToCart(id);

        Assert.assertEquals(cart.getCartProductPrice(1), this.shop.getProductPrice(id));

        this.cart.removeProductFromCard(1);
    }

    @Test
    public void testAddMultipleProducts() {
        int quantity = 3;
        for (int i = 1; i <= quantity; i++) {
            this.shop.addFeaturedProductToCart(i);
        }

        Assert.assertEquals(cart.getProductsCount(), quantity);

        for (int i = 0; i < quantity; i++) {
            this.cart.removeProductFromCard(1);
        }
    }
}
