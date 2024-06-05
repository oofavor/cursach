package org.example.task1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.example.reusable.WebDriveSingleton;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LambdaTest {
    WebDriver driver = WebDriveSingleton.getInstance();

    private By headerElements = By.cssSelector("h1,h2,h3,h4,h5,h6");
    private By bodyElement = By.cssSelector("body");

    public void openPage() {
        driver.get("https://lambdatest.github.io/sample-todo-app/");
    }

    public List<String> getHeaders() {
        List<WebElement>  headers = driver.findElements(headerElements);
        return headers
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public String getText() {
        return driver.findElement(bodyElement).getText();
    }

    public String getTaskElementClass(int num) {
        return driver
                .findElement(By.cssSelector("input" + "[name=\"li"+num+"\"] + span"))
                .getAttribute("class");
    }

    public LambdaTest selectTaskElement(int num) {
        driver
                .findElement(By.cssSelector("input" + "[name=\"li" + num + "\"]"))
                .click();

        return this;
    }

    public int getAmountOfUncheckedTaskElements() {
        String str = driver.findElement(By.cssSelector(".ng-binding")).getText();

        return Integer.parseInt(str.split(" of")[0]);
    }

    public LambdaTest insertTaskElement(String name) {
        driver
                .findElement(By.id("sampletodotext"))
                .sendKeys(name);

        driver.findElement(By.id("addbutton")).click();
        return this;
    }

    public int getTotalAmountOfTaskElements() {
        String str = driver.findElement(By.cssSelector(".ng-binding")).getText();

        return Integer.parseInt(str.split(" of ")[1].split(" ")[0]);
    }
}
