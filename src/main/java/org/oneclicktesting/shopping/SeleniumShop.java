package org.oneclicktesting.shopping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumShop implements Shop {

    private final String BASE_URL = "http://automationpractice.com/index.php";

    private WebDriver driver;

    public SeleniumShop(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void addFeaturedProductToCart(int id) {
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

    @Override
    public float getProductPrice(int id) {
        return 0;
    }
}
