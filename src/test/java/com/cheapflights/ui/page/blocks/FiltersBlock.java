package com.cheapflights.ui.page.blocks;

import com.cheapflights.ui.page.abstractpages.AbstractHomePage;
import com.cheapflights.ui.page.abstractpages.AbstractSearchPage;

import com.cheapflights.ui.utils.BrowserUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;


@Name("Filters container")
@FindBy(xpath = "//div[@class='filterListContainer']")
public class FiltersBlock extends HtmlElement {

    @Name("Two stops checkbox")
    @FindBy(xpath = "//label[@data-name=\"2\"]")
    private CheckBox twoStops;

    @Name("One stop checkbox")
    @FindBy(xpath = "//label[@data-name=\"1\"]")
    private CheckBox oneStop;

    @Name("Max flight duration sider")
    @FindBy(xpath = "//div[@aria-label='Maximum flight duration']")
    private WebElement slider;

    @Name("Page load progress bar")
    @FindBy(xpath = "//div[contains(@id, 'legdur-content')]//div[contains(@class,'activeRange')]")
    private WebElement progress;

    @Name("Sort section dropdown")
    @FindBy(xpath = "//div[@data-name='sort-section']")
    private WebElement sortSection;

    @Name("Sort section dropdown values")
    @FindBy(xpath = "//div[@data-name='sort-section']//div//div/span")
    private WebElement sortSectionValue;

    private SortDropDownBlock sortDropDownBlock;

    private Logger logger = LogManager.getLogger();

    private WebDriver driver = AbstractHomePage.getDriver();

    public void chooseNonStopFlights() {
        logger.debug("Waiting for the search results page to load");
        BrowserUtils.waitForJSandJQueryToLoad(driver);

        logger.debug("Unchecking one stop checkbox");
        BrowserUtils.click(driver, oneStop);

        BrowserUtils.waitForJSandJQueryToLoad(driver);
        try {
            logger.debug("Unchecking two stops checkbox");
            BrowserUtils.click(driver, twoStops);
            logger.debug("Waiting for the page to update according to the chosen filters");
        } catch (NoSuchElementException e) {
            BrowserUtils.waitForJSandJQueryToLoad(driver);
            logger.error("There are no flights with 2 or more stops");
        }
    }

    public void modifyDuration(int divider, int multiplier) {
        //Here Javascript is used as a workaround as moveToElement doesn't scroll the element into view in ForeFox and MoveTargetOutOfBoundsException is thrown
        //https://github.com/mozilla/geckodriver/issues/776
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", slider);
        Dimension size = progress.getSize();
        int sliderWidth = size.getWidth();

        logger.debug("Modifying flight duration");
        Actions builder = new Actions(driver);

        builder.moveToElement(slider)
                .click()
                .dragAndDropBy
                        (slider, -((sliderWidth / divider) * multiplier), 0)
                .build()
                .perform();
        logger.debug("Waiting for the page to update according to the chosen duration");
        BrowserUtils.waitForJSandJQueryToLoad(driver);

    }

    public void sortByCheapest() {
        if (!(sortSectionValue.getText().equals("Cheapest"))) {
            logger.debug("Opening sorting drop-down");
            BrowserUtils.click(driver, sortSection);
            BrowserUtils.waitForVisibilityFluently(AbstractSearchPage.getDriver(), sortDropDownBlock, 10, 1);
            sortDropDownBlock.sortByCheapest();
        }

    }


}
