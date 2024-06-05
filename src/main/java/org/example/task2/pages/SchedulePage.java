package org.example.task2.pages;

import org.example.reusable.WebDriveSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SchedulePage {

    public WebDriver driver = WebDriveSingleton.getInstance();

    private By foundGroups = By.cssSelector(".found-groups");
    private By foundGroupsChildren = By.cssSelector(".found-groups > *");
    private By schedule = By.cssSelector(".schedule");
    private By groups = By.cssSelector(".groups");
    private By weekElements = By.cssSelector(".schedule-week > *");
    private By currentDay = By.cssSelector(".schedule-day__title");

    public void openPage() {
        driver.get("https://rasp.dmami.ru/");
    }

    public boolean switchToThisPage() {
        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if(!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                return true;
            }
        }
        return false;
    }

    public SchedulePage inputGroupNumber(String groupNumber) {
        driver
            .findElement(groups)
            .sendKeys(groupNumber);
        return this;
    }

    public SchedulePage clickSearchByGroupNumber(String groupNumber) {
        WebDriveSingleton.getWait().until(
                d -> !d
                    .findElement(foundGroups)
                    .getCssValue("display").equals("none")
        );
        driver.findElement(By.id(groupNumber)).click();

        return this;
    }

    public int getAmountOfFoundGroups() {
        WebDriveSingleton.getWait().until(
                d -> !d
                        .findElement(foundGroups)
                        .getCssValue("display").equals("none")
        );
        return driver.findElements(foundGroupsChildren).size();
    }

    public String getHighlightedDay() {
        WebDriveSingleton.getWait().until(
                d -> !d
                        .findElement(schedule)
                        .getCssValue("display").equals("none")
        );

        List<WebElement> week = driver.findElements(weekElements);

        for (WebElement day : week) {
            if (day.getAttribute("class").contains("schedule-day_today")) {
                return day.findElement(currentDay).getText();
            }
        }
        return "";
    }

    public String getDayHighlight(String currentDay) {
        WebDriveSingleton.getWait().until(
                d -> !d
                        .findElement(schedule)
                        .getCssValue("display").equals("none")
        );

        return driver.findElement(
                By.xpath("//*[contains(text(),'%s')]//parent::div".formatted(currentDay)))
                .getCssValue("background-color");
    }
}
