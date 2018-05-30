package com.cheapflights.ui.utils;

import org.apache.commons.io.FileUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BrowserUtils {

    private static Logger logger = LogManager.getLogger();


    public static void waitForVisibilityFluently(WebDriver driver, WebElement element, int timeout, int poll) {
        try {
            new FluentWait(driver).withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofSeconds(poll))
                    .ignoring(org.openqa.selenium.NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.error("WebDriver failed to find " + element.toString() + " within the specified time frame", e);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("WebDriver was unable to locate  " + element.toString(), e);
        }

    }

    public static void waitForInvisibilityExplicitly(WebDriver driver, WebElement element, int timeout) {
        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOf(element));
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.error("WebDriver failed to find " + element.toString() + " within the specified time frame", e);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("The driver was unable to locate " + element.toString(), e);
        }
    }

    public static void waitForAttributeToBe(WebDriver driver, By by, String attribute, String value, int timeout) {
        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.attributeToBe(by, attribute, value));
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.error("WebDriver failed to find the element with " + by.toString() + " locator within the specified time frame", e);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("The driver was unable to locate with " + by.toString() + " locator", e);
        }
    }

    public static boolean waitForJSandJQueryToLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        ExpectedCondition<Boolean> jQueryLoad = driver1 -> {
            try {
                return ((Long) ((JavascriptExecutor) driver1).executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                return true;
            }
        };
        ExpectedCondition<Boolean> jsLoad = driver12 -> ((JavascriptExecutor) driver12).executeScript("return document.readyState")
                .toString().equals("complete");
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public static void takeScreenshot(WebDriver driver) {
        try {
            String path = "target/screenshots/";
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String pathName = path + System.nanoTime() + ".png";
            FileUtils.copyFile(src, new File(pathName));
            logger.info("RP_MESSAGE#FILE#{}#{}", pathName, "Refer to the screenshot with the following name " + pathName + " for more details");
        } catch (IOException e) {
            logger.error("Failure to take a screenshot" + e.getMessage(), e);
        }
    }

    public static WebElement highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        return element;
    }

    public static WebElement unhighlightElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        return element;
    }

    public static void click(WebDriver driver, WebElement element) {
        highlightElement(driver, element);
        element.click();
        unhighlightElement(driver, element);

    }

//    public static void sendKeys(WebDriver driver, WebElement element, CharSequence... keysToSend){
//        highlightElement(driver, element);
//        element.sendKeys(keysToSend);
//        unhighlightElement(driver, element);
//    }
}

