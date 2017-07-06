package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import org.openqa.selenium.By;

/**
 * Created by bawa.onkar on 06/07/17.
 */
public class Search {
    public UIElement searchField = new UIElement(By.xpath("//XCUIElementTypeSearchField"));
    public UIElement search = new UIElement(By.name("Search"));
}
