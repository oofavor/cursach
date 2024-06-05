package org.example.task2.pages;

import org.example.reusable.WebDriveSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MospolytechSchedulePage {
    WebDriver driver = WebDriveSingleton.getInstance();

    public void openPage() {
        driver.get("https://www.mospolytech.ru/obuchauschimsya/raspisaniya");
    }

    public SchedulePage clickOpenSchedulePage() {
        driver.findElement(By.xpath("//a[@href='https://rasp.dmami.ru/']")).click();

        return new SchedulePage();
    }
}
