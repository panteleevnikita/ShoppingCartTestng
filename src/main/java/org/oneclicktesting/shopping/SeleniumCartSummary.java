package org.oneclicktesting.shopping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumCartSummary implements CartSummary {

    private WebDriver driver;
    private final String CART_SUMMARY_URL = "http://automationpractice.com/index.php?controller=order";

    public SeleniumCartSummary(WebDriver driver) {
        this.driver = driver;
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


    }
}
