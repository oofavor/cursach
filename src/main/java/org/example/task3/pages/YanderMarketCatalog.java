package org.example.task3.pages;

import org.example.reusable.WebDriveSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class YanderMarketCatalog {
    WebDriver driver = WebDriveSingleton.getInstance();

    By laptopCategory = By.xpath("//*[text()= 'Ноутбуки и компьютеры']");
    By laptopTab = By.xpath("//*[text()='Ноутбуки']");

    public void openComputerAndLaptopsTab() {
        WebElement computersTab = driver.findElement(laptopCategory);

        Actions action = new Actions(driver);
        action.moveToElement(computersTab).perform();
    }

    public YandexMarketSearched openLaptopPage() {
        driver.findElement(laptopTab).click();

        return new YandexMarketSearched();
    }
}
