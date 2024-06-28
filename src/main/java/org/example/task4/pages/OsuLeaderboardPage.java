package org.example.task4.pages;

import org.example.reusable.WebDriveSingleton;
import org.example.task4.Player;
import org.example.task4.Record;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class OsuLeaderboardPage {
    WebDriver driver = WebDriveSingleton.getInstance();
    By record = By.cssSelector(".ranking-page-table__row");
    By name = By.cssSelector(".ranking-page-table__user-link:last-child");
    By performancePoints = By.cssSelector(".ranking-page-table__column.ranking-page-table__column--focused");
    By userLink = By.cssSelector(".ranking-page-table__user-link-text");

    public OsuLeaderboardPage openPage() {
        driver.get("https://osu.ppy.sh/rankings/osu/performance");
        return this;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<Record> getRecords() {
        List<WebElement> records = driver.findElements(record);
        List<Record> recordList = new ArrayList<Record>();

        for (WebElement current : records) {
            String playerName = current.findElement(name).getText();
            int pp = Integer.parseInt(current.findElement(performancePoints).getText().replace(",",""));

            recordList.add(new Record(playerName, pp));
        }

        return recordList;
    }

    public WebElement pickRandomPlayer() {
        return driver.findElement(record);
    }

    public OsuPlayerPage clickOnPlayer(WebElement playerElement) {
        playerElement.findElement(userLink).click();

        return new OsuPlayerPage();
    }

    public String getUsername(WebElement playerElement) {
        return playerElement.findElement(name).getText();
    }
}
