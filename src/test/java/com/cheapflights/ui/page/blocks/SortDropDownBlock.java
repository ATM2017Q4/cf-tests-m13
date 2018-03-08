package com.cheapflights.ui.page.blocks;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.logging.Logger;

@Name("Sorting dropdown")
@FindBy(xpath = "//ul[@class = 'dropdownList']")
public class SortDropDownBlock extends HtmlElement{

    @FindBy(xpath = "//li[@data-title='Cheapest']")
    private WebElement cheapestFlights;

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    public void sortByCheapest() {
        logger.info("Sorting the results by cheapest");
        cheapestFlights.click();
    }

}
