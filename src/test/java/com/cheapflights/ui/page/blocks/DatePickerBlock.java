package com.cheapflights.ui.page.blocks;

import com.cheapflights.ui.page.abstractpages.AbstractHomePage;

import com.cheapflights.ui.utils.BrowserUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@Name("Date picker")
@FindBy(xpath = "//div[@class='contentContainer']")
public class DatePickerBlock extends HtmlElement {

    @Name("Arrow to switch months")
    @FindBy(css = "div[aria-label='Next month']")
    private WebElement nextArrow;

    @Name("Column with month name")
    @FindBy(xpath = "(//div[contains(@class, \"col col-month col-month\")])[3]")
    private WebElement monthColumn;

    @Name("Name of the month")
    @FindBy(xpath = "(//div[contains(@class, \"col col-month col-month\")])[3]//div[@class='monthDisplay']")
    private WebElement monthName;

    @Name("Table with dates for the chosen month")
    @FindBy(xpath = "(//div[@class='weeks'])[3]//div[@class='day']")
    private List<WebElement> dates;

    @Name("Return date field")
    @FindBy(xpath = "//div[contains(text(), \"Return\")]")
    private WebElement returnDateField;

    private Logger logger = LogManager.getLogger();

    private WebDriver driver = AbstractHomePage.getDriver();

    public void searchDates(String month, String startDate, String endDate) {
        logger.debug("Clicking the next button until finding the searched month");
        while (!(isVisible(monthColumn, monthName, month))) {
            BrowserUtils.click(driver, nextArrow);

        }
        logger.debug("Selecting the intended dates");
        By endDateLocator = By.xpath("(//div[@class='weeks'])[3]//div[contains(text(), '" + endDate + "')]");
        List<WebElement> duration = dates;
        for (WebElement day : duration) {
            if (day.getText().equals(startDate)) {
                BrowserUtils.click(driver, day);
                break;
            }
        }
        BrowserUtils.click(driver, returnDateField);
        BrowserUtils.click(driver, driver.findElement(endDateLocator));

    }

    public boolean isVisible(WebElement monthColumn, WebElement monthName, String text) {
        boolean result;
        try {
            result = (monthColumn.getAttribute("aria-hidden").equals("false")) && monthName.getText().contains(text);

        } catch (org.openqa.selenium.NoSuchElementException e) {
            result = false;
        }
        return result;
    }


}
