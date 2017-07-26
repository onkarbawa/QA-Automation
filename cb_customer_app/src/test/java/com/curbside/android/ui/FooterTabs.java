package com.curbside.android.ui;

import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

/**
 * @author hitesh.grover
 * Footer tab that contains icons for Shop/ Map/ Cart and My Account
 */

public class FooterTabs extends AbstractScreen {
	public UIElement btnMyAccount = UIElement.byUISelector("new UiSelector().text(\"Account\")");
	public UIElement btnShop = UIElement.byUISelector("new UiSelector().text(\"Shop\")");
	public UIElement btnMap = UIElement.byUISelector("new UiSelector().text(\"Map\")");
	public UIElement btnCart = UIElement.byUISelector("new UiSelector().text(\"Cart\")");
	public UIElement iconCart = UIElement.byXpath("//android.widget.TextView[@text='Cart']/preceding-sibling::android.widget.ImageView");
	
	public FooterTabs() {
	}

    public void tapMyAccount() throws Throwable {
    	btnMyAccount.tap();
    }

    @When("^I tap Map on footer$")
    public void tapMap() throws Throwable {
	    btnMap.tap();
    }

    @And("^I tap Shop on footer$")
    public void tapShop() throws Throwable {
        btnShop.waitFor(5).tap();
        if(!homeScreen.shopNearLabel.isDisplayed()){
            btnShop.tap();
        }
    }

    @And("^I go to Cart screen$")
    public void tapCart() throws Throwable {
        btnCart.tap();
    }
}
