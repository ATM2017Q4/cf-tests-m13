package com.cheapflights.ui.page.blocks;
import com.cheapflights.ui.utils.LoggerUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Sorting dropdown")
@FindBy(xpath = "//ul[@class = 'dropdownList']")
public class SortDropDownBlock extends HtmlElement{

    @FindBy(xpath = "//li[@data-title='Cheapest']")
    private WebElement cheapestFlights;

    public void sortByCheapest() {
        LoggerUtil.info("Sorting the results by cheapest");
        cheapestFlights.click();
    }

}
