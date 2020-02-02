package org.oneclicktesting.shopping;

import org.openqa.selenium.WebDriver;

public class SeleniumShop implements Shop {

    private WebDriver driver;

    public SeleniumShop(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void addFeaturedProductToCart(int id, int quantity) {

    }
}
