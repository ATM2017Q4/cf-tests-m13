package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.support.ui.FluentWait;

public abstract class WaitDecorator implements IWait {
    protected Wait wait;

    public WaitDecorator(Wait wait){
        this.wait = wait;
    }

    @Override
    public void setUpWait(){
       wait.setUpWait();
    }

    @Override
    public FluentWait createWait(){
        return wait.createWait();
    }






}
