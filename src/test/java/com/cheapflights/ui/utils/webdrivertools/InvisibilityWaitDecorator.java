package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class InvisibilityWaitDecorator extends WaitDecorator {

    public InvisibilityWaitDecorator(Wait wait) {
        super(wait);
    }

    @Override
    public void setUpWait() {
        super.setUpWait();
        this.createWait().until(waitForInvisibility(wait.getWebElement()));

    }

    @Override
    public FluentWait createWait() {
        return super.createWait();
    }


    public ExpectedCondition<Boolean> waitForInvisibility(WebElement element){
        return ExpectedConditions.invisibilityOf(element);

    }

}
