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
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
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
        this.cartSummary.removeProductFromCard(id, 1);
    }

    @Test
    public void testAddMultipleProducts() {
        int quantity = 3;
        for (int i = 1; i <= quantity; i++) {
            this.shop.addFeaturedProductToCart(i);
        }

        Assert.assertEquals(cartSummary.getProductsCount(), quantity);

        for (int i = 0; i < quantity; i++) {
            this.cartSummary.removeProductFromCard(1, quantity-i);
        }
    }

    @Test
    public void testAddSameProductToCartMultipleTimes() {
        int quantity = 3;
        int id = 1;

        for (int i = 1; i <= quantity; i++) {
            this.shop.addFeaturedProductToCart(id);
        }
        Assert.assertEquals(cartSummary.getCartProductQuantity(id), quantity);
        this.cartSummary.removeProductFromCard(id, 1);
    }

    @Test
    public void testIncrementProduct() {
        int incrementQuantity = 2;
        int id = 2;
        this.shop.addFeaturedProductToCart(id);

        for (int i = 1; i <= incrementQuantity; i++) {
            this.cartSummary.incrementProduct(1);
        }

        Assert.assertEquals(cartSummary.getCartProductQuantity(1), incrementQuantity + 1);

        this.cartSummary.removeProductFromCard(1, 1);
    }

    @Test
    public void testDecrementProduct() {
        int decrementQuantity = 2;
        int totalQuantity = 3;
        int id = 3;

        for (int i = 0; i < totalQuantity; i++) {
            this.shop.addFeaturedProductToCart(id);
        }

        for (int i = 1; i <= decrementQuantity; i++) {
            this.cartSummary.decreaseProduct(1);
        }
        Assert.assertEquals(cartSummary.getCartProductQuantity(1), totalQuantity - decrementQuantity);
        this.cartSummary.removeProductFromCard(1, 1);
    }

    @Test
    public void testProductPrice() {
        int id = 3;
        this.shop.addFeaturedProductToCart(id);

        Assert.assertEquals(cartSummary.getCartProductPrice(1), this.shop.getProductPrice(id));

        this.cartSummary.removeProductFromCard(1, 1);
    }

    @Test
    public void testTotalPriceOfMultipleProducts() {
        int totalQuantity = 3;

        float totalPrice = 0;

        for (int i = 1; i <= totalQuantity; i++) {
            this.shop.addFeaturedProductToCart(i);
            totalPrice += this.shop.getProductPrice(i);
        }

        Assert.assertEquals(cartSummary.getCartTotalPrice(), totalPrice);

        for (int i = 0; i < totalQuantity; i++) {
            this.cartSummary.removeProductFromCard(1, totalQuantity - i);
        }
    }
}
