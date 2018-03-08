package com.cheapflights.ui.page.pageobjects;

import com.cheapflights.ui.page.abstractpages.AbstractSearchPage;
import com.cheapflights.ui.utils.webdrivertools.AjaxContentWaitDecorator;
import com.cheapflights.ui.utils.webdrivertools.InvisibilityWaitDecorator;
import com.cheapflights.ui.utils.webdrivertools.VisibilityWaitDecorator;
import com.cheapflights.ui.utils.webdrivertools.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.logging.Level;

public class SecondFlightSearchPage extends AbstractSearchPage {
    public SecondFlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[contains(text(), 'Cheapest')]")
    private WebElement cheapestFlight;

    @FindBy(css = "a[data-index='3']")
    private WebElement stopsFilter;

    @FindBy(css = "label[for='filters-stops-multi']")
    private WebElement multiStops;

    @FindBy(css = "label[for='filters-stops-one']")
    private WebElement oneStop;

    @FindBy(css = "a[data-index='2']")
    private WebElement durationFilter;

    @FindBy(css = "div[id='duration-slider-outbound'] div[class='handle']")
    private WebElement slider;

    @FindBy(css = "div[id='duration-slider-outbound'] div[class='slider-bar']")
    private WebElement progress;

    @FindBy(css = "span[class='handle-top-items']>i[class='icon-arrow-down']")
    private WebElement closeButton;

    @FindBy(id = "update-indicator")
    private WebElement updateIndicator;

    private String cheapestFlightXpath = "//div[@class='quicklink cheapest clearfix selected-filter']//span[@class='value']";

    @Override
    public SecondFlightSearchPage chooseNonStopFlights() {
        try {
            logger.info("Waiting for the search results page to load");
            new VisibilityWaitDecorator(new Wait(driver, cheapestFlight, 300, 10)).setUpWait();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.log(Level.SEVERE, "Driver was unable to locate the element: either the page didn't load properly or the element doesn't exist");
            new VisibilityWaitDecorator(new Wait(driver, cheapestFlight, 150, 10)).setUpWait();
        } finally {
            logger.info("Opening stops filter tab");
            stopsFilter.click();
            new AjaxContentWaitDecorator(new Wait(AbstractSearchPage.getDriver())).setUpWait();
            logger.info("Unchecking one stop checkbox");
            oneStop.click();
            logger.info("Unchecking multi stops checkbox");
            multiStops.click();
        }
        return this;
    }

    @Override
    public SecondFlightSearchPage modifyDuration(int divider, int multiplier) {
        logger.info("Opening duration tab");
        durationFilter.click();
        Dimension size = progress.getSize();
        int sliderWidth = size.getWidth();
        Actions builder = new Actions(driver);
        logger.info("Modifying flight duration");
        builder
                .dragAndDropBy
                        (slider, -((sliderWidth / divider) * multiplier), 0)
                .build()
                .perform();
        return this;
    }

    @Override
    public void closeFilters() {
        logger.info("Closing filters tab");
        closeButton.click();
        logger.info("Waiting for page to update in accordance with the chosen filters");
        new InvisibilityWaitDecorator(new Wait(driver, updateIndicator, 10)).setUpWait();
    }

    @Override
    public int getCheapestFlight() {
        logger.info("Finding the element with the ");
        String cheapestFlight = driver.findElement(By.xpath(cheapestFlightXpath)).getText();
        int sum = Integer.parseInt(cheapestFlight);
        return sum;
    }

}
