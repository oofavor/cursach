package org.example.reusable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriveSingleton {
        public static WebDriver driver = new ChromeDriver();

        public static WebDriver getInstance() {
            return driver;
        }

        public static WebDriverWait getWait() {
            return new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        public static void waitTillAppear(By element) {
            getWait().until(ExpectedConditions.elementToBeClickable(element));
        }

        public static void waitTillUrl(String url) {
            getWait().until(ExpectedConditions.urlContains(url));
        }
}
