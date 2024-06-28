package org.example.task4.pages;

import org.example.reusable.WebDriveSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OsuPage {
    WebDriver driver = WebDriveSingleton.getInstance();

    By beatmapButton = By.cssSelector(".landing-nav__section > *:nth-child(2)");


    public OsuPage openPage() {
        driver.get("https://osu.ppy.sh/");
        return this;
    }

    public String getTitle() {
        return driver.getTitle();
    }


    public OsuBeatmapCatalogPage openBeatmapCatalog() {
        WebDriveSingleton.waitTillAppear(beatmapButton);
        driver.findElement(beatmapButton).click();

        return new OsuBeatmapCatalogPage();
    }
}
