package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


public class AjaxContentWaitDecorator extends WaitDecorator{
    public AjaxContentWaitDecorator(Wait wait) {
        super(wait);
    }

    @Override
    public void setUpWait() {
        super.setUpWait();
        this.createWait().until(waitForJSandJQueryToLoad());

    }

    @Override
    public FluentWait createWait() {
        return super.createWait();
    }


    public ExpectedCondition<Boolean> waitForJSandJQueryToLoad(){

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        return ExpectedConditions.and((jQueryLoad),(jsLoad));

    }
}
