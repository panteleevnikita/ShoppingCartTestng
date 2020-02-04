package org.oneclicktesting.shopping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumCartSummary implements CartSummary {

    private WebDriver driver;
    private final String CART_SUMMARY_URL = "http://automationpractice.com/index.php?controller=order";
    private WebDriverWait wait;

    public SeleniumCartSummary(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    @Override
    public int getCartProductQuantity(int id) {
        driver.get(CART_SUMMARY_URL);

        String deleteElementXpathTemplate = "//table[@id='cart_summary']/tbody/tr[%d]//input[@type='text']";
        String deleteXpath = String.format(deleteElementXpathTemplate, id);

        WebElement productQuantity = driver.findElement(By.xpath(deleteXpath));
        return Integer.parseInt(productQuantity.getAttribute("value"));
    }

    @Override
    public void removeProductFromCard(int id) {
        driver.get(CART_SUMMARY_URL);

        String deleteElementXpathTemplate = "//table[@id='cart_summary']/tbody/tr[%d]//td[@data-title='Delete']/div/a[@title='Delete']";
        String deleteXpath = String.format(deleteElementXpathTemplate, id);

        WebElement deleteLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(deleteXpath)));
        deleteLink.click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(deleteXpath), 0));
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
    public int getProductsCount() {
        return 0;
    }

    @Override
    public void incrementProduct() {

    }

    @Override
    public void decrementProduct() {

    }
}
