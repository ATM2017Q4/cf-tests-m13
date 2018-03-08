package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface IWebDriverTools {
    static void waitForVisibilityFluently(WebDriver driver, WebElement element, int timeout, int poll){};

    static void waitForInvisibilityExplicitly(WebDriver driver, WebElement element, int timeout){};

    static void waitForAttributeToBe(WebDriver driver, By by, String attribute, String value, int timeout){};

    static boolean waitForJSandJQueryToLoad(WebDriver driver){return false;};
}
