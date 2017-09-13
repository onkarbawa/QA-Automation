package com.cap.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.SwipeDirection;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.xpath.SourceTree;
import org.testng.Assert;

/**
 * Created by bawa.onkar
 */

public class Tasks extends AbstractScreen {

    UIElement taskBar = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Tasks')]");
    UIElement errorMessage = UIElement.byXpath("//XCUIElementTypeTable[1]/XCUIElementTypeStaticText[1]");
    UIElement btnClaim = UIElement.byName("Claim");
    UIElement btnMineTasks = UIElement.byXpath("//XCUIElementTypeButton[contains(@name,'Mine')]");

    @Then("^I should see '(.*)' screen$")
    public void iShouldSeeScreen(String screen) throws Throwable {
        UIElement screenName = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+screen+"')]");
        screenName.waitFor(10);
        Assert.assertTrue(screenName.getText().contains(screen));
    }

    @Given("^I search for selected Order ID and claim it$")
    public void iSearchForSelectedOrderIDAndClaimIt() throws Throwable {
      //  String orderID = Properties.getVariable("orderID");
        UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'E0GRFTW8')]").scrollTo().tap();
        btnClaim.tap();
        btnClaim.waitForNot(7);
        Steps.tapButton("Close");
    }

    @And("^I tap on Mine tab$")
    public void iTapOnMineTab() throws Throwable {
        btnMineTasks.waitFor(2).tap();
    }

    @And("^I search for selected Order$")
    public void iSearchForSelectedOrder() throws Throwable {
        //  String orderID = Properties.getVariable("orderID");
        footerTabsScreen.tapMyAccount();
        footerTabsScreen.tapTask();
        UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'E0GRFTW8')]").scrollTo().tap();
    }

    @Then("^I should see product details as below for CAP$")
    public void iShouldSeeProductDetailsAsBelowForCAP() throws Throwable {
        productDetailScreen.firstProduct.tap();
        Assert.assertTrue(productDetailScreen.productName.waitFor(15).isDisplayed(),"Product name is not displayed");
        productDetailScreen.productRetailerWebsiteLink.tap();
        Assert.assertEquals(productDetailScreen.redirectLinkPage.waitFor(10).getText(),"Product at Retailer",
                "Link is not redirected to the desired page");
        commonSteps.iTapOnBackButton();
        Assert.assertTrue(productDetailScreen.productSKU.isDisplayed(),"Product sku is not displayed");
        Assert.assertTrue(productDetailScreen.productDescription.isDisplayed(),"Product description is not displayed");
        productDetailScreen.productOverview.scrollTo();
        Assert.assertTrue(productDetailScreen.productOverview.isDisplayed(),"Product overview is not displayed");
    }
}
