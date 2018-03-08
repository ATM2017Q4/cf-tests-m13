package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class AttributeWaitDecorator extends WaitDecorator{

    public AttributeWaitDecorator(Wait wait) {
        super(wait);
    }

    @Override
    public void setUpWait() {
        super.setUpWait();
        this.createWait().until(waitForAttribute(wait.getBy(), wait.getAttribute(), wait.getValue()));

    }

    @Override
    public FluentWait createWait() {
        return super.createWait();
    }


    public ExpectedCondition<Boolean> waitForAttribute(By by, String attribute, String value){
        return ExpectedConditions.attributeToBe(by, attribute, value);

    }
}
