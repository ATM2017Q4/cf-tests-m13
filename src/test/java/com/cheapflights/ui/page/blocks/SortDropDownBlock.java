package com.cheapflights.ui.page.blocks;
import com.cheapflights.ui.page.abstractpages.AbstractHomePage;
import com.cheapflights.ui.utils.BrowserUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Sorting dropdown")
@FindBy(xpath = "//ul[@class = 'dropdownList']")
public class SortDropDownBlock extends HtmlElement{

    @FindBy(xpath = "//li[@data-title='Cheapest']")
    private WebElement cheapestFlights;

    private Logger logger = LogManager.getLogger();

    private WebDriver driver = AbstractHomePage.getDriver();

    public void sortByCheapest() {
        logger.debug("Sorting the results by cheapest");
        BrowserUtils.click(driver, cheapestFlights);
    }

}
