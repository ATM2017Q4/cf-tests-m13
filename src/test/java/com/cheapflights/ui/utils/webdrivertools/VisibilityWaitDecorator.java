package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class VisibilityWaitDecorator extends WaitDecorator {
    public VisibilityWaitDecorator(Wait wait) {
        super(wait);
    }

    @Override
    public void setUpWait() {
        super.setUpWait();
        this.createWait()
                .pollingEvery(wait.getPoll(), TimeUnit.SECONDS)
                .withTimeout(wait.getTimeout(), TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.NoSuchElementException.class).
                until(waitForVisibility(wait.getWebElement()));

    }

    @Override
    public FluentWait createWait() {
        return super.createWait();
    }


    public ExpectedCondition<WebElement> waitForVisibility(WebElement element){
        return ExpectedConditions.visibilityOf(element);

    }

}
