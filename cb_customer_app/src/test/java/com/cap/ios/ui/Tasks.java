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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by bawa.onkar
 */

public class Tasks extends AbstractScreen {

    UIElement taskBar = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Tasks')]");
    UIElement errorMessage = UIElement.byXpath("//XCUIElementTypeTable[1]/XCUIElementTypeStaticText[1]");
    UIElement btnClaim = UIElement.byName("Claim");

    UIElement btnAll = UIElement.byName("All");
    UIElement btnIssue = UIElement.byName("Issue");

    @Then("^I should see '(.*)' screen$")
    public void iShouldSeeScreen(String screen) throws Throwable {
        UIElement screenName = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+screen+"')]");
        screenName.waitFor(10);
        Assert.assertTrue(screenName.getText().contains(screen));
    }

    @Given("^I search for '(.*)' Order ID and claim it$")
    public void iSearchForOrderIDAndClaimIt(String orderAlias) throws Throwable {
        btnAll.waitFor(10);
        String orderID = Properties.getVariable(orderAlias);
        UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+orderID+"')]").scrollTo().tap();
  //      UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'2MYC20AQ')]").scrollTo().tap();
        btnClaim.tap();
        btnClaim.waitForNot(7);
        Steps.tapButton("Close");
    }

    @And("^I tap on '(.*)' tab$")
    public void iTapOnTab(String button) throws Throwable {
        UIElement btnTasks = UIElement.byXpath("//XCUIElementTypeButton[contains(@name,'"+button+"')]");
        btnTasks.waitFor(2).tap();
    }

    @And("^I search for (.*) OrderID$")
    public void iSearchForSelectedOrder(String orderAlias) throws Throwable {
        String orderID = Properties.getVariable(orderAlias);
     //   footerTabsScreen.tapMyAccount();
      //  footerTabsScreen.tapTask();
        UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+orderID+"')]").scrollTo().tap();
    //    UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'2MYC20AQ')]").scrollTo().tap();
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
        Assert.assertEquals(productDetailScreen.productOverview.getText(),"Overview","Product overview is not displayed");
    }

    @And("^I should see claim product in Mine tab list with '(.*)' button$")
    public void iShouldSeeClaimProductInMineTabListWithButton(String button) throws Throwable {
        String orderID = Properties.getVariable("orderID");
        footerTabsScreen.tapMyAccount();
        footerTabsScreen.tapTask();

        Assert.assertTrue(UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+orderID+"')]")
                .scrollTo(SwipeDirection.UP).getText().contains(orderID),"Product is not in the Mine Tab");

        Assert.assertEquals(UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+orderID+"')]/following-sibling::XCUIElementTypeButton").getText(),
                button,"Unclaim button is not present in list");

    }

    @And("^I (?:search|look) for '(.*)' Order Id under '(.*)' tab")
    public void iSearchForOrderId(String orderAlias, String tabName) throws Throwable {
        String orderID = Properties.getVariable(orderAlias);
        footerTabsScreen.tapMyAccount();
        footerTabsScreen.tapTask();

        Assert.assertTrue(UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+orderID+"')]").scrollTo(SwipeDirection.UP).getText().contains(orderID),"Product is not in the Mine Tab");

        Assert.assertEquals(UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+orderID+"')]/following-sibling::XCUIElementTypeButton").getText(),"Unclaim","Unclaim button is not present in list");
    }

    @Given("^I mark all items as '(.*)'$")
    public void iMarkAllItemsAs(String button) throws Throwable {
        if (btnIssue.isDisplayed()){
            int totalIssueButton = btnIssue.getCount();
            for (int i = 1; i <= totalIssueButton; i++){
                UIElement.byXpath("//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Items to Pick')]]/following-sibling::XCUIElementTypeCell["+i+"]//XCUIElementTypeButton[@name='Issue']").scrollTo(SwipeDirection.UP).tap();
                issueScreen.iTurnONItemNotAvailable("ON",button);
                Steps.tapButton("Done");
            }
            Steps.tapButton("Finish");
        }
    }
}
