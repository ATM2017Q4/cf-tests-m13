package com.cheapflights.ui.page.pageobjects;

import com.cheapflights.ui.page.abstractpages.AbstractHomePage;
import com.cheapflights.ui.page.blocks.PrefilledSearchFormBlock;
import com.cheapflights.ui.utils.LoggerUtil;
import org.openqa.selenium.WebDriver;


public class PrefilledHomePage extends AbstractHomePage {

    public PrefilledHomePage(WebDriver driver) {
        super(driver);
    }

    private PrefilledSearchFormBlock searchForm;


    @Override
    public PrefilledHomePage chooseOrigin(String from) {
        LoggerUtil.info("Setting the origin to " + from);
        searchForm.searchOrigin(from);
        return this;
    }

    @Override
    public PrefilledHomePage chooseDestination(String to) {
        LoggerUtil.info("Setting the destination to " + to);
        searchForm.searchDestination(to);
        return this;
    }

    @Override
    public PrefilledHomePage chooseDates(String period, String startDate, String endDate) {
        LoggerUtil.info("Choosing the dates of departure and arrival");
        searchForm.searchDates(period, startDate, endDate);
        return this;
    }

    @Override
    public PrefilledHomePage increaseNumberOfAdults(int number) {
        LoggerUtil.info("Increasing the number of adults");
        searchForm.increaseNumberOfAdults(number);
        return this;
    }


}


