package org.example.task2.pages;

import org.example.reusable.WebDriveSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MospolytechPage {

    WebDriver driver = WebDriveSingleton.getInstance();

    public void openPage() {
        driver.get("https://www.mospolytech.ru/");
    }

    public MospolytechSchedulePage clickScheduleButton() {
        driver.findElement(By.xpath("//*[@title=\"Расписание\"]")).click();
        return new MospolytechSchedulePage();
    }
}
