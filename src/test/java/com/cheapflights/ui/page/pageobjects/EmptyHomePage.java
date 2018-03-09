package com.cheapflights.ui.page.pageobjects;

import com.cheapflights.ui.page.abstractpages.AbstractHomePage;

import com.cheapflights.ui.utils.LoggerUtil;
import com.cheapflights.ui.utils.webdrivertools.WebDriverToolsDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmptyHomePage extends AbstractHomePage {

    public EmptyHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='next-month']")
    private WebElement nextButton;

    @FindBy(xpath = "//th[@class='-monthname']/span")
    private WebElement monthName;

    @FindBy(css = "svg[class='icon -departureDate']")
    private WebElement departureField;

    @FindBy(css = "svg[class='icon -returnDate']")
    private WebElement arrivalField;

    @FindBy(xpath = "//span[@class='cfui -field -spinner -numAdults']//a[@class='spin-up']")
    private WebElement adultsPlus;

    @FindBy(css = "div[class='lookup']")
    private WebElement options;

    private final By departureDates = By.xpath("//div/table[1]//td");
    private final By returnDates = By.xpath("//div/table[1]//td");

    @Override
    public EmptyHomePage chooseOrigin(String from) {
        LoggerUtil.info("Clicking in the origin field and clearing it");
        origin.click();

        LoggerUtil.info("Sending " + from + "as origin name");
        origin.sendKeys(from);

        LoggerUtil.info("Waiting for the dropdown to appear");
        WebDriverToolsDecorator.waitForVisibilityFluently(driver, options, 2, 1);

        LoggerUtil.info("Choosing the origin and all airports");
        origin.sendKeys(Keys.ARROW_DOWN);
        origin.sendKeys(Keys.ENTER);
        return this;
    }

    @Override
    public EmptyHomePage chooseDestination(String to) {
        LoggerUtil.info("Clicking in the destination field and clearing it");
        destination.click();

        LoggerUtil.info("Sending " + to + "as destination name");
        destination.sendKeys(to);

        LoggerUtil.info("Waiting for the dropdown to appear");
        WebDriverToolsDecorator.waitForVisibilityFluently(driver, options, 2, 1);

        LoggerUtil.info("Choosing the destination and all airports");
        destination.sendKeys(Keys.ARROW_DOWN);
        destination.sendKeys(Keys.ENTER);
        return this;
    }

    @Override
    public EmptyHomePage chooseDates(String period, String startDate, String endDate) {
        LoggerUtil.info("Clicking in the departure date field");
        departureField.click();

        LoggerUtil.info("Searching for " + period + "and selecting dates of departure");
        while (!(monthName.getText().contains(period.toUpperCase()))) {
            nextButton.click();

        }
        List<WebElement> departure = driver.findElements(departureDates);

        for (WebElement cell : departure) {
            if (cell.getText().equals(startDate)) {
                cell.click();
                break;
            }
        }

        LoggerUtil.info("Clicking in the arrival date field");
        arrivalField.click();

        LoggerUtil.info("Searching for " + period + "and selecting dates of arrival");
        List<WebElement> arrival = driver.findElements(returnDates);
        for (WebElement cell : arrival) {
            if (cell.getText().equals(endDate)) {
                cell.click();
                break;
            }
        }
        return this;
    }

    @Override
    public EmptyHomePage increaseNumberOfAdults(int number) {
        LoggerUtil.info("Increasing the number of adults to " + number);
        for (int i = 1; i < number; i++) {
            adultsPlus.click();
        }
        return this;
    }

}
