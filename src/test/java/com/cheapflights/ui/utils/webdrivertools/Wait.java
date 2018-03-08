package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.logging.Logger;

public class Wait implements IWait {

    private WebDriver driver;

    private WebElement webElement;
    private int timeout;
    private By by;
    private String attribute;
    private String value;
    private int poll;


    public Wait(WebDriver driver,WebElement webElement, int timeout) {
        this.driver = driver;
        this.webElement = webElement;
        this.timeout = timeout;
    }

    public Wait(WebDriver driver, By by, String attribute, String value, int timeout) {
        this.driver = driver;
        this.by = by;
        this.attribute = attribute;
        this.value = value;
        this.timeout = timeout;
    }

    public Wait(WebDriver driver, WebElement webElement, int timeout, int poll) {
        this.driver = driver;
        this.webElement = webElement;
        this.timeout = timeout;
        this.poll = poll;

    }


    public Wait(WebDriver driver) {
        this.driver = driver;
    }
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void setUpWait() {
        logger.info("Choosing the wait");
    }

    @Override
    public FluentWait createWait() {
        return new FluentWait(driver);
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public int getTimeout() {
        return timeout;
    }

    public By getBy() {
        return by;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getValue() {
        return value;
    }

    public int getPoll() {
        return poll;
    }

    public WebDriver getDriver() {
        return driver;
    }

}
