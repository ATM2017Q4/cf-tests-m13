package com.cheapflights.ui.page.blocks;

import com.cheapflights.ui.page.abstractpages.AbstractHomePage;

import com.cheapflights.ui.utils.LoggerUtil;
import com.cheapflights.ui.utils.webdrivertools.WebDriverToolsDecorator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;

@Name("Search Form")
@FindBy(xpath = "//div[@class=\"searchFormWrapper \"]")
public class PrefilledSearchFormBlock extends BaseSearchFormBlock {

    private WebDriver driver = AbstractHomePage.getDriver();

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

    @Override
    public void searchOrigin(String from) {
        LoggerUtil.info("Clicking in the origin field and clearing it");
        origin.click();
        origin.clear();

        LoggerUtil.info("Sending " + from + "as origin name");
        origin.sendKeys(from);

        LoggerUtil.info("Waiting for the dropdown to appear");
        WebDriverToolsDecorator.waitForVisibilityFluently(driver, originOptions, 2, 1);

        LoggerUtil.info("Choosing the origin and all airports");
        origin.sendKeys(Keys.ENTER);
    }

    @Override
    public void searchDestination(String to) {
        LoggerUtil.info("Clicking in the destination field and clearing it");
        destination.click();
        LoggerUtil.info("Sending " + to + "as destination name");
        destination.sendKeys(to);
        LoggerUtil.info("Waiting for the dropdown to appear");
        WebDriverToolsDecorator.waitForVisibilityFluently(driver, destinationOptions, 2, 1);
        LoggerUtil.info("Choosing the destination and all airports");
        destination.sendKeys(Keys.ENTER);
    }

    @Override
    public void searchDates(String month, String startDate, String endDate) {
        LoggerUtil.info("Clicking in the departure date field");
        departureDateField.click();
        LoggerUtil.info("Waiting for the date picker to appear");
        WebDriverToolsDecorator.waitForVisibilityFluently(driver, datePicker, 10, 1);
        LoggerUtil.info("Searching for " + month + "and selecting dates of departure and arrival");
        datePickerBlock.searchDates(month, startDate, endDate);
    }

    @Override
    public void increaseNumberOfAdults(int number) {
        LoggerUtil.info("Opening the form with the number of adults");
        travellersNumber.click();
        LoggerUtil.info("Increasing the number of adults to " + number);
        for (int i = 1; i < number; i++) {
            adultsPlus.click();
        }
        LoggerUtil.info("Closing the form");
        closeButton.click();
    }

}
