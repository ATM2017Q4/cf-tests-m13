package com.cheapflights.common.driver;

import org.openqa.selenium.WebDriver;

public abstract class AbstractWebDriver {
    protected WebDriver driver;

    public abstract WebDriver getDriver();
}
