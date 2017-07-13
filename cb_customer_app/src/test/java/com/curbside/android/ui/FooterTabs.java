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
	public FooterTabs() {
	}
	//TODO
//	UIElement shopTabButton = new UIElement(By.xpath("//android.widget.FrameLayout/android.widget.LinearLayout[@index='0']/" +
//			"android.widget.FrameLayout[@index='0']"));
//	UIElement mapTabButton = new UIElement(By.xpath("//android.widget.FrameLayout/android.widget.LinearLayout[@index='0']/" +
//			"android.widget.FrameLayout[@index='1']"));
//	UIElement cartTabButton = new UIElement(By.xpath("//android.widget.FrameLayout/android.widget.LinearLayout[@index='0']/" +
//			"android.widget.FrameLayout[@index='2']"));
//	UIElement accountTabButton = new UIElement(By.xpath("//android.widget.FrameLayout/android.widget.LinearLayout[@index='0']/" +
//			"android.widget.FrameLayout[@index='3']"));

    public void tapMyAccount() throws Throwable {
        Steps.tapButton("Account");
    }

    @When("^I tap Map on footer$")
    public void tapMap() throws Throwable {
	    Steps.tapButton("Map");
    }

    public void tapShop() throws Throwable {
        Steps.tapButton("Shop");
    }

    @And("^I go to Cart screen$")
    public void tapCart() throws Throwable {
        Steps.tapButton("Cart");
    }
}
