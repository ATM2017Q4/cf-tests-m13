package com.cheapflights.common.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class NewChromeDriver extends AbstractWebDriver {
    @Override
    public WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver");
        options.addArguments("start-maximized");
        ChromeDriver driver = new ChromeDriver(options);
        return driver;
    }
}
