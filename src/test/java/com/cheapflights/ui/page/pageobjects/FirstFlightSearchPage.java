package com.cheapflights.ui.page.pageobjects;

import com.cheapflights.ui.page.abstractpages.AbstractSearchPage;
import com.cheapflights.ui.page.blocks.FiltersBlock;

import com.cheapflights.ui.utils.BrowserUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FirstFlightSearchPage extends AbstractSearchPage {

    public FirstFlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='Common-Results-ProgressBar Hidden']")
    private WebElement progressBar;

    private FiltersBlock filtersBlock;

    private By loadComplete = By.xpath("//div[@class='resultsContainer']/div[contains(@id, 'cover')]");

    private By overlay = By.xpath("//div[@class=\"current-search\"]");

    private static String cheapestFlightXpath = "(//div[@class='above-button']//a[@class='booking-link']/span[@class='price option-text'])[1]";


    @Override
    public FirstFlightSearchPage chooseNonStopFlights() {
        BrowserUtils.waitForVisibilityFluently(driver, driver.findElement(overlay), 15, 2);
        driver.findElement(By.xpath("//body")).sendKeys(Keys.ESCAPE);
        //logger.info("Waiting for the progress bar to disappear");
        BrowserUtils.waitForInvisibilityExplicitly(driver, progressBar, 100);
        logger.info("Choosing non stop flights");
        BrowserUtils.waitForJSandJQueryToLoad(AbstractSearchPage.getDriver());
        filtersBlock.chooseNonStopFlights();
        return this;
    }

    @Override
    public FirstFlightSearchPage modifyDuration(int divider, int multiplier) {
        logger.info("Modifying flight duration");
        filtersBlock.modifyDuration(divider, multiplier);
        BrowserUtils.waitForAttributeToBe(driver, loadComplete, "class", "resultsListCover tl", 20);
        return this;
    }

    @Override
    public FirstFlightSearchPage sortByCheapest() {
        logger.info("Sorting the flight by cheapest");
        filtersBlock.sortByCheapest();
        BrowserUtils.waitForAttributeToBe(driver, loadComplete, "class", "resultsListCover tl", 20);
        return this;
    }

    @Override
    public int getCheapestFlight() {
        logger.info("Getting the cheapest flight in the results");
        String[] price;
        int sum;
        String cheapestFlight = driver.findElement(By.xpath(cheapestFlightXpath)).getText();
        price = cheapestFlight.split("\\$");
        sum = Integer.parseInt(price[1]);
        BrowserUtils.highlightElement(driver, driver.findElement(By.xpath(cheapestFlightXpath)));
        return sum;
    }

}
