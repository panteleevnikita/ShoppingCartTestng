package org.oneclicktestng.shopping;

import org.oneclicktesting.shopping.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class CartSummaryTest {
    CartSummary cartSummary;
    Shop shop;
    private WebDriver driver;

    private final String BASE_URL = "http://automationpractice.com/index.php";
    private final String CART_SUMMARY_URL = BASE_URL + "?controller=order";

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
        driver.close();
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

        addFeaturedProduct(id, quantity);
        Assert.assertEquals(cartSummary.getCartProductQuantity(id), quantity);
        removeProductFromCard(id);
//        getCartProductQuantity(id);
    }

    @Test
    public void testAddSameProductToCartMultipleTimes() {
        int quantity = 3;
        int id = 1;

        addFeaturedProduct(id, quantity);
        Assert.assertEquals(cartSummary.getCartProductQuantity(id), quantity);
        removeProductFromCard(id);
    }

    private void addFeaturedProduct(int id, int quantity) {
        for (int i = 0; i < quantity; i++) {
            driver.get(BASE_URL);

            String featuredProductBlockTemplate = "#homefeatured > .ajax_block_product:nth-child(%d)";
            String featuredProductTemplate = "#homefeatured > .ajax_block_product:nth-child(%d) .button:nth-child(%d) > span";
            String firstFeaturedProduct = String.format(featuredProductTemplate, id, id);
            String firstFeaturedProductBlock = String.format(featuredProductBlockTemplate, id);

            WebElement productBlock = driver.findElement(By.cssSelector(firstFeaturedProductBlock));
            WebElement addLink = driver.findElement(By.cssSelector(firstFeaturedProduct));
            WebDriverWait wait = new WebDriverWait(driver, 5);

            Actions action = new Actions(driver);
            action.moveToElement(productBlock).build().perform();
            wait.until(ExpectedConditions.visibilityOf(addLink));

            addLink.click();

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("layer_cart"))));
        }
    }

    private void removeProductFromCard(int id) {
        driver.get(CART_SUMMARY_URL);
        String deleteElementXpathTemplate = "//table[@id='cart_summary']/tbody/tr[%d]//td[@data-title='Delete']/div/a[@title='Delete']";
        String deleteXpath = String.format(deleteElementXpathTemplate, id);
        WebDriverWait wait = new WebDriverWait(driver, 5);

        WebElement deleteLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(deleteXpath)));
        deleteLink.click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(deleteXpath), 0));
    }
}
