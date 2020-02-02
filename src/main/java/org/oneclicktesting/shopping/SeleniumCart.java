package org.oneclicktesting.shopping;

import org.openqa.selenium.WebDriver;

public class SeleniumCart implements Cart {

    private WebDriver driver;

    public SeleniumCart(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public int getCartProductQuantity(int id) {
        return 0;
    }

    @Override
    public void removeProductFromCard(int id) {

    }
}
