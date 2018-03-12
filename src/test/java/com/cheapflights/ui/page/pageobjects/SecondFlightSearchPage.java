package com.cheapflights.ui.page.pageobjects;

import com.cheapflights.ui.page.abstractpages.AbstractSearchPage;


import com.cheapflights.ui.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


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
            logger.debug("Waiting for the search results page to load");
            BrowserUtils.waitForVisibilityFluently(driver, cheapestFlight, 300, 10);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("Driver was unable to locate the element: either the page didn't load properly or the element doesn't exist");
            BrowserUtils.waitForVisibilityFluently(driver, cheapestFlight, 150, 10);
        } finally {
            logger.debug("Opening stops filter tab");
            stopsFilter.click();
            BrowserUtils.waitForJSandJQueryToLoad(AbstractSearchPage.getDriver());
            logger.debug("Unchecking one stop checkbox");
            oneStop.click();
            logger.debug("Unchecking multi stops checkbox");
            multiStops.click();
        }
        return this;
    }

    @Override
    public SecondFlightSearchPage modifyDuration(int divider, int multiplier) {
        logger.debug("Opening duration tab");
        durationFilter.click();
        Dimension size = progress.getSize();
        int sliderWidth = size.getWidth();
        Actions builder = new Actions(driver);
        logger.debug("Modifying flight duration");
        builder
                .dragAndDropBy
                        (slider, -((sliderWidth / divider) * multiplier), 0)
                .build()
                .perform();
        return this;
    }

    @Override
    public void closeFilters() {
        logger.debug("Closing filters tab");
        closeButton.click();
        logger.debug("Waiting for page to update in accordance with the chosen filters");
        BrowserUtils.waitForInvisibilityExplicitly(driver, updateIndicator, 10);
    }

    @Override
    public int getCheapestFlight() {
        logger.debug("Finding the element with the ");
        String cheapestFlight = driver.findElement(By.xpath(cheapestFlightXpath)).getText();
        int sum = Integer.parseInt(cheapestFlight);
        return sum;
    }

}
