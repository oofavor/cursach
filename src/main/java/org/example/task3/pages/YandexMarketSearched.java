package org.example.task3.pages;

import org.example.reusable.WebDriveSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class YandexMarketSearched {
    WebDriver driver = WebDriveSingleton.getInstance();

    By listOnSale = By.cssSelector("*[data-auto-themename=\"listDetailed\"]");
    By itemTitle = By.cssSelector("*[data-auto=\"snippet-title\"]");
    By itemPrice = By.cssSelector("*[data-auto=\"snippet-price-current\"]");
    By itemIsUsed = By.cssSelector("*[data-auto=\"resale-badge\"]");
    By filterResale = By.cssSelector("*[data-filter-value-id=\"resale_resale\"]");
    By loadingState = By.cssSelector("*[data-auto=\"SerpStatic-loader\"]");

    public void selectFirstSearches(int num) {
        List<WebElement> elementList = driver.findElements(listOnSale);
        for (int i = 0; i < num; i++) {
            WebElement current = elementList.get(i);

            System.out.println(current.findElement(itemTitle).getText());
            System.out.println(current.findElement(itemPrice).getText());
            System.out.println(current.findElements(itemIsUsed).isEmpty());
        }

    }

    public void clickShowOnlyUsed() {
        driver.findElement(filterResale).click();
        // waiting for the catalog to get into the loading state
        // smh it doesnt get into it instantly
        WebDriveSingleton.getWait().until(d -> !d.findElements(loadingState).isEmpty());
        WebDriveSingleton.getWait().until(d -> d.findElements(loadingState).isEmpty());
    }
}
