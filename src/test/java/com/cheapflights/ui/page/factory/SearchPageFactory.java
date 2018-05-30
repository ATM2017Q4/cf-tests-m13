package com.cheapflights.ui.page.factory;

import com.cheapflights.ui.page.abstractpages.AbstractSearchPage;
import com.cheapflights.ui.page.pageobjects.FirstFlightSearchPage;
import com.cheapflights.ui.page.pageobjects.SecondFlightSearchPage;
import org.openqa.selenium.WebDriver;


public class SearchPageFactory {

    static String searchPageUrl = "https://global.cheapflights.com/flight-search/";


    public static AbstractSearchPage getCorrectPage(WebDriver driver) {
        if (driver.getCurrentUrl().contains(searchPageUrl)) {
            return new FirstFlightSearchPage(driver);
        } else {
            return new SecondFlightSearchPage(driver);
        }
    }
}
