package org.oneclicktesting.shopping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumCart implements Cart {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String BASE_URL = "http://automationpractice.com/index.php";


    public SeleniumCart(WebDriver driver) {
        this.driver = driver;
         wait = new WebDriverWait(driver, 5);
    }

    @Override
    public int getCartProductQuantity(int id) {
        driver.get(BASE_URL);

        String quantityTemplate = "//dl[@class='products']/dt[%d]//span[@class='quantity']";
        String quantityPath = String.format(quantityTemplate, id);
        WebElement quantityElement = driver.findElement(By.xpath(quantityPath));

        return Integer.parseInt(quantityElement.getAttribute("textContent"));
    }

    @Override
    public float getCartProductPrice(int id) {
        driver.get(BASE_URL);
        String productPriceTemplate = "//dl[@class='products']/dt[%d]//span[@class='price']";
        String productPricePath = String.format(productPriceTemplate, id);
        WebElement productPrice = driver.findElement(By.xpath(productPricePath));

        return Float.parseFloat(productPrice.getAttribute("textContent").trim().replaceAll("\\$", ""));
    }

    @Override
    public float getCartTotalPrice() {
        String cartProductPath = "//span[@class='ajax_cart_total']";
        WebElement totalPrice = driver.findElement(By.xpath(cartProductPath));

        float totalPriceValue = Float.parseFloat(totalPrice.getAttribute("textContent").trim().replaceAll("\\$", ""));

        return totalPriceValue;
    }

    @Override
    public void removeProductFromCard(int id) {
        openCart();

        String deleteLinkTemplate = "//dl[@class='products']/dt[%d]//a[@class='ajax_cart_block_remove_link']";
        String deleteLinkPath = String.format(deleteLinkTemplate, id);

        WebElement deleteLink = driver.findElement(By.xpath(deleteLinkPath));
        wait.until(ExpectedConditions.visibilityOf(deleteLink));
        deleteLink.click();
    }

    @Override
    public int getProductsCount() {
        driver.get(BASE_URL);
        String cartProductPath = "//dl[@class='products']/dt";
        return driver.findElements(By.xpath(cartProductPath)).size();
    }

    private void openCart() {
        driver.get(BASE_URL);

        String cartTitle = "//a[@title = 'View my shopping cart']";
        String cartBlock = "//div[contains(@class, 'cart_block')]";
        WebElement cartTitleElement = driver.findElement(By.xpath(cartTitle));
        WebElement cartBlockElement = driver.findElement(By.xpath(cartBlock));

        Actions action = new Actions(driver);
        action.moveToElement(cartTitleElement).build().perform();
        wait.until(ExpectedConditions.visibilityOf(cartBlockElement));
    }
}
