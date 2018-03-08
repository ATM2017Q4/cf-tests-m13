package com.cheapflights.ui.page.blocks;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

public abstract class BaseSearchFormBlock extends HtmlElement{

    @Name("Origin box")
    @FindBy(name = "origin")
    protected TextInput origin;

    @Name("Destination box")
    @FindBy(name = "destination")
    protected TextInput destination;

    public abstract void searchOrigin(String from);
    public abstract void searchDestination(String to);
    public abstract void searchDates(String month, String startDate, String endDate);
    public abstract void increaseNumberOfAdults(int number);




}
