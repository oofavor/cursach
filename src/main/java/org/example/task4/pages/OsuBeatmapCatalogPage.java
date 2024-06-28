package org.example.task4.pages;

import org.example.reusable.WebDriveSingleton;
import org.example.task4.Beatmap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class OsuBeatmapCatalogPage {
    WebDriver driver = WebDriveSingleton.getInstance();
    By beatmaps = By.cssSelector(".beatmapsets__item");
    By beatmapName = By.cssSelector(".beatmapset-panel__info-row--title > a");
    By beatmapAuthor = By.cssSelector(".beatmapset-panel__info-row--artist > a");
    By beatmapMapper = By.cssSelector(".beatmapset-panel__info-row--mapper > div > a");
    By beatmapStatus = By.cssSelector(".beatmapset-status");
    By beatmapTitle = By.cssSelector(".js-react--beatmaps");

    public OsuBeatmapCatalogPage openPage() {
        driver.get("https://osu.ppy.sh/beatmapsets");
        return this;
    }

    public String getTitle() {
        WebDriveSingleton.waitTillAppear(beatmapTitle);
        return driver.getTitle();
    }

    public List<Beatmap> getBeatmaps(int amount) {
        WebDriveSingleton.waitTillAppear(beatmaps);

        List<Beatmap> beatmapList = new ArrayList<>();
        List<WebElement> beatmapElement = driver.findElements(beatmaps);
        for (int i = 0; i < amount; i++) {
            WebElement current = beatmapElement.get(i);

            String name = current.findElement(beatmapName).getText();
            String author = current.findElement(beatmapAuthor).getText();
            String mapper = current.findElement(beatmapMapper).getText();
            String status = current.findElement(beatmapStatus).getText();

            beatmapList.add(new Beatmap(name, author, mapper, status));
        }

        return beatmapList;
    }
}
