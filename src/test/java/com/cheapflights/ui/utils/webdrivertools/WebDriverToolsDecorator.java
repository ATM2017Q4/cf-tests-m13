package com.cheapflights.ui.utils.webdrivertools;
import com.cheapflights.ui.utils.LoggerUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverToolsDecorator implements IWebDriverTools {

    private static WebDriverTools webDriverTools;

    public WebDriverToolsDecorator(WebDriverTools webDriverTools) {
        this.webDriverTools = webDriverTools;
    }

    public static void waitForVisibilityFluently(WebDriver driver, WebElement element, int timeout, int poll) {
        try {
            webDriverTools.waitForVisibilityFluently(driver, element, timeout, poll);
        } catch (org.openqa.selenium.TimeoutException e) {
            LoggerUtil.error("WebDriver failed to find the element within the specified time frame", e);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            LoggerUtil.error("WebDriver was unable to locate the element", e);
        }

    }

    public static void waitForInvisibilityExplicitly(WebDriver driver, WebElement element, int timeout) {
        try {
            webDriverTools.waitForInvisibilityExplicitly(driver, element, timeout);
        } catch (org.openqa.selenium.TimeoutException e) {
            LoggerUtil.error("WebDriver failed to find the element within the specified time frame", e);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            LoggerUtil.error("The driver was unable to locate the element", e);
        }
    }

    public static void waitForAttributeToBe(WebDriver driver, By by, String attribute, String value, int timeout) {
        try {
            webDriverTools.waitForAttributeToBe(driver, by, attribute, value, timeout);
        } catch (org.openqa.selenium.TimeoutException e) {
            LoggerUtil.error("WebDriver failed to find the element within the specified time frame", e);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            LoggerUtil.error("The driver was unable to locate the element", e);
        }
    }

    public static boolean waitForJSandJQueryToLoad(WebDriver driver) {
        try {
            webDriverTools.waitForJSandJQueryToLoad(driver);
        } catch (org.openqa.selenium.TimeoutException e) {
            LoggerUtil.error("The page failed failed to properly load within the specified time frame", e);
            return false;
        }
        return true;
    }
}
