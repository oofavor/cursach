package org.example.task1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.example.reusable.WebDriveSingleton;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LambdaTestPage {
    WebDriver driver = WebDriveSingleton.getInstance();

    private By headerElements = By.cssSelector("h1,h2,h3,h4,h5,h6");
    private By bodyElement = By.cssSelector("body");
    private By counterElement = By.cssSelector(".ng-binding");
    private By inputElement = By.id("sampletodotext");
    private By formSubmitButton = By.id("addbutton");

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

    public LambdaTestPage selectTaskElement(int num) {
        driver
                .findElement(By.cssSelector("input" + "[name=\"li" + num + "\"]"))
                .click();

        return this;
    }

    public int getAmountOfUncheckedTaskElements() {
        String str = driver.findElement(counterElement).getText();

        return Integer.parseInt(str.split(" of")[0]);
    }

    public int getTotalAmountOfTaskElements() {
        String str = driver.findElement(counterElement).getText();

        return Integer.parseInt(str.split(" of ")[1].split(" ")[0]);
    }

    public LambdaTestPage insertTaskElement(String name) {
        driver
                .findElement(inputElement)
                .sendKeys(name);

        driver.findElement(formSubmitButton).click();
        return this;
    }

}
