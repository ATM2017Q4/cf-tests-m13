package com.cheapflights.ui.tests;

import com.cheapflights.common.driver.AbstractWebDriver;
import com.cheapflights.common.driver.DriverFactory;
import com.cheapflights.ui.entities.TravelInfo;
import com.cheapflights.ui.page.abstractpages.AbstractHomePage;
import com.cheapflights.ui.page.factory.HomePageFactory;
import com.cheapflights.ui.page.factory.SearchPageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class CheapFlightsTest {

    private WebDriver driver;
    private AbstractHomePage homePage;
    private final String url = "https://cheapflights.com/";
    private TravelInfo travelInfo;

    public CheapFlightsTest(TravelInfo travelInfo){
        this.travelInfo=travelInfo;
    }

    @BeforeClass(alwaysRun = true)
    public void launchBrowser() {
        AbstractWebDriver instance = DriverFactory.getDriverFromFactory("firefox");
        driver = instance.getDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeClass(dependsOnMethods = "launchBrowser", description = "Add implicit wait and maximize window", alwaysRun = true)
    public void openUrl() {
        driver.get(url);

    }

    @Test(description = "Fill in form and get the cheapest flight")
    public void chooseTheCheapestFlight() {
        HomePageFactory pageFactory = new HomePageFactory(driver);

        homePage = pageFactory.getCorrectPage(driver);
        homePage.chooseOrigin(travelInfo.getOrigin())
                .chooseDestination(travelInfo.getDestination())
                .chooseDates(travelInfo.getDepartureDates().getMonth(), Integer.toString(travelInfo.getDepartureDates().getDay()), Integer.toString(travelInfo.getReturnDates().getDay()))
                .increaseNumberOfAdults(travelInfo.getNumberOfAdults())
                .submitForm()
                .chooseNonStopFlights()
                .modifyDuration(4, 3)
                .sortByCheapest()
                .closeFilters();
        Assert.assertTrue(SearchPageFactory.getCorrectPage(driver).getCheapestFlight() <= travelInfo.getAcceptablePrice());

    }

    @AfterClass(description = "Close browser", alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
