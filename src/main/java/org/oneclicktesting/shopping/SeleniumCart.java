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
        return 1;
    }

    @Override
    public int getCartProductPrice(int id) {
        return 0;
    }

    @Override
    public int getCartTotalPrice() {
        return 0;
    }

    @Override
    public void removeProductFromCard(int id) {
        openCart();

    }

    @Override
    public int getProductsCount() {
        return 0;
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
