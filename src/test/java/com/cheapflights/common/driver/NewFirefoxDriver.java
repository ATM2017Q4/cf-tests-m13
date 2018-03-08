package com.cheapflights.common.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class NewFirefoxDriver extends AbstractWebDriver {
    @Override
    public WebDriver getDriver() {
        FirefoxOptions options = new FirefoxOptions();
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver");
        options.addArguments("start-maximized");
        options.setProfile(new FirefoxProfile());
        FirefoxDriver driver = new FirefoxDriver(options);
        return driver;
    }
}
