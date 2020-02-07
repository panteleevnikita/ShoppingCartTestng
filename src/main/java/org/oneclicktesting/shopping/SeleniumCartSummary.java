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
    public void removeProductFromCard(int id, int productCount) {
        driver.get(CART_SUMMARY_URL);

        String deleteElementXpathTemplate = "//table[@id='cart_summary']/tbody/tr[%d]//td[@data-title='Delete']/div/a[@title='Delete']";
        String deleteXpath = String.format(deleteElementXpathTemplate, id);

        WebElement deleteLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(deleteXpath)));
        deleteLink.click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//table[@id='cart_summary']//tr[contains(@class, 'cart_item')]"), productCount-1));
    }

    @Override
    public float getCartProductPrice(int id) {
        driver.get(CART_SUMMARY_URL);

        String productPriceTemplate = "//table[@id='cart_summary']//tr[%d]//span[@class='price']/span[@class='price']";
        String productPricePath = String.format(productPriceTemplate, id);
        WebElement productPrice = driver.findElement(By.xpath(productPricePath));

        return Float.parseFloat(productPrice.getText().trim().replaceAll("$", ""));
    }

    @Override
    public float getCartTotalPrice() {
        driver.get(CART_SUMMARY_URL);

        WebElement totalPrice = driver.findElement(By.id("total_product"));
        return Integer.parseInt(totalPrice.getText().trim().replaceAll("$", ""));
    }

    @Override
    public int getProductsCount() {
        driver.get(CART_SUMMARY_URL);

        String cartProductPath = "//table[@id='cart_summary']//tr[contains(@class, 'cart_item')]";
        return driver.findElements(By.xpath(cartProductPath)).size();
    }

    @Override
    public void incrementProduct(int id) {
        driver.get(CART_SUMMARY_URL);

        String deleteElementXpathTemplate = "//table[@id='cart_summary']/tbody/tr[%d]//a[@title, 'Add']";
        String deleteXpath = String.format(deleteElementXpathTemplate, id);

        WebElement deleteLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(deleteXpath)));
        deleteLink.click();
    }

    @Override
    public void decrementProduct(int id) {
        driver.get(CART_SUMMARY_URL);

        String deleteElementXpathTemplate = "//table[@id='cart_summary']/tbody/tr[%d]//a[@title, 'Subtract']";
        String deleteXpath = String.format(deleteElementXpathTemplate, id);

        WebElement deleteLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(deleteXpath)));
        deleteLink.click();
    }
}
