package org.example.task3.pages;

import org.example.reusable.WebDriveSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class YandexMarketPage {
    WebDriver driver = WebDriveSingleton.getInstance();

    By catalogButton = By.cssSelector("*[data-zone-name=\"catalog\"]");
    By catalogDiv = By.cssSelector("*[data-zone-name=\"catalog-content\"]");

    public void openPage() {
        driver.get("https://market.yandex.ru/");
    }

    public YanderMarketCatalog openCatalog() {
        driver.findElement(catalogButton).click();
        WebDriveSingleton.getWait().until( d -> !d.findElements(catalogDiv).isEmpty() );

        return new YanderMarketCatalog();
    }
}
