package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.support.ui.FluentWait;

public interface IWait {
    void setUpWait();

    FluentWait createWait();

}
