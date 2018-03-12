package com.cheapflights.ui.page.blocks;

import com.cheapflights.ui.page.abstractpages.AbstractHomePage;

import com.cheapflights.ui.utils.BrowserUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;

@Name("Search Form")
@FindBy(xpath = "//div[@class=\"searchFormWrapper \"]")
public class PrefilledSearchFormBlock extends BaseSearchFormBlock {

    @Name("Origin typeahead dropdown")
    @FindBy(xpath = "//div[contains(@id, 'origin-smartbox-dropdown')]")
    private WebElement originOptions;

    @Name("Destination typeahead dropdown")
    @FindBy(xpath = "//div[contains(@id, 'destination-smartbox-dropdown')]")
    private WebElement destinationOptions;

    @Name("Departure date field")
    @FindBy(xpath = "//div[@aria-label='Departure date input']")
    private WebElement departureDateField;

    @Name("Date picker")
    @FindBy(xpath = "//div[@class='contentContainer']")
    private WebElement datePicker;

    @Name("Close button for travelers block")
    @FindBy(xpath = "//div[@class=\"cabinTravelerWrapper\"]//div[@class='close']")
    private WebElement closeButton;

    @Name("Number of adult travellers")
    @FindBy(xpath = "//a[@aria-label='Select number of travelers and cabin class']")
    private WebElement travellersNumber;

    @Name("Increase in number of adults")
    @FindBy(xpath = "(//div[contains(@id, 'adults')]//button[@title=\"Increment\"])[2]")
    private Button adultsPlus;

    private DatePickerBlock datePickerBlock;

    private WebDriver driver = AbstractHomePage.getDriver();

    @Override
    public void searchOrigin(String from) {
        logger.debug("Clicking in the origin field and clearing it");
        BrowserUtils.highlightElement(driver, origin);
        origin.click();
        origin.clear();

        logger.debug("Sending " + from + "as origin name");
        origin.sendKeys(from);
        logger.debug("Waiting for the dropdown to appear");
        BrowserUtils.waitForJSandJQueryToLoad(driver);

        logger.debug("Choosing the origin and all airports");
        origin.sendKeys(Keys.ENTER);
        BrowserUtils.unhighlightElement(driver, origin);
    }

    @Override
    public void searchDestination(String to) {
        logger.debug("Clicking in the destination field and clearing it");
        BrowserUtils.highlightElement(driver, destination);
        destination.click();

        logger.debug("Sending " + to + "as destination name");
        destination.sendKeys(to);

        logger.debug("Waiting for the dropdown to appear");
        BrowserUtils.waitForVisibilityFluently(driver, destinationOptions, 2, 1);

        logger.debug("Choosing the destination and all airports");
        destination.sendKeys(Keys.ENTER);
        BrowserUtils.unhighlightElement(driver, destination);
    }

    @Override
    public void searchDates(String month, String startDate, String endDate) {
        logger.debug("Clicking in the departure date field");
        BrowserUtils.click(driver, departureDateField);

        logger.debug("Waiting for the date picker to appear");
        BrowserUtils.waitForVisibilityFluently(driver, datePicker, 10, 1);

        logger.debug("Searching for " + month + "and selecting dates of departure and arrival");
        datePickerBlock.searchDates(month, startDate, endDate);
    }

    @Override
    public void increaseNumberOfAdults(int number) {
        logger.debug("Opening the form with the number of adults");
        BrowserUtils.click(driver, travellersNumber);
        logger.debug("Increasing the number of adults to " + number);
        for (int i = 1; i < number; i++) {
            BrowserUtils.click(driver, adultsPlus);
        }
        logger.debug("Closing the form");
        BrowserUtils.click(driver, closeButton);
    }

}
