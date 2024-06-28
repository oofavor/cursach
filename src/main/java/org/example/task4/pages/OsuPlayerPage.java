package org.example.task4.pages;

import org.example.reusable.WebDriveSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OsuPlayerPage {
    WebDriver driver = WebDriveSingleton.getInstance();


    By username = By.cssSelector(".profile-info__name > span");
    By profileInfo = By.cssSelector(".profile-info");

    public String getTitle() {
        WebDriveSingleton.waitTillAppear(profileInfo);

        return driver.getTitle();
    }

    public String getUsername() {
        WebDriveSingleton.waitTillAppear(profileInfo);

        return driver.findElement(username).getText();
    }

}
