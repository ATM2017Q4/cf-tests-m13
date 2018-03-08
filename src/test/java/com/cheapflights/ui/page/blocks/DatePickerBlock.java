package com.cheapflights.ui.page.blocks;

import com.cheapflights.ui.page.abstractpages.AbstractHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;
import java.util.logging.Logger;

@Name("Date picker")
@FindBy(xpath = "//div[@class='contentContainer']")
public class DatePickerBlock extends HtmlElement {

    @Name("Arrow to switch months")
    @FindBy(css = "div[aria-label='Next month']")
    private WebElement nextArrow;

    @Name("Column with month name")
    @FindBy(xpath = "(//div[@class=\"col col-month col-month-m\"])[2]")
    private WebElement monthColumn;

    @Name("Name of the month")
    @FindBy(xpath = "(//div[@class=\"col col-month col-month-m\"])[2]//div[@class='monthDisplay']")
    private WebElement monthName;

    @Name("Table with dates for the chosen month")
    @FindBy(xpath = "(//div[@class='weeks'])[3]//div[@class='day']")
    private List<WebElement> dates;

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    public void searchDates(String month, String startDate, String endDate) {
        logger.info("Clicking the next button until finding the searched month");
        while (!(isVisible(monthColumn, monthName, month))) {
            nextArrow.click();
        }
        logger.info("Selecting the intended dates");
        By endDateLocator = By.xpath("(//div[@class='weeks'])[3]//div[contains(text(), '" + endDate + "')]");
        List<WebElement> duration = dates;
        for (WebElement day : duration) {
            if (day.getText().equals(startDate)) {
                Actions chooser = new Actions(AbstractHomePage.getDriver());
                chooser.click(day)
                        .sendKeys(Keys.TAB)
                        .moveToElement(AbstractHomePage.getDriver().findElement(endDateLocator))
                        .click(AbstractHomePage.getDriver().findElement(endDateLocator))
                        .build().perform();
                break;
            }
        }
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
