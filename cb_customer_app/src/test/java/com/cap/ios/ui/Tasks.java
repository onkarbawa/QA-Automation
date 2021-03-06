package com.cap.ios.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;


/**
 * Created by bawa.onkar
 */

public class Tasks extends AbstractScreen {

    UIElement taskBar = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Tasks')]");
    UIElement errorMessage = UIElement.byXpath("//XCUIElementTypeTable[1]/XCUIElementTypeStaticText[1]");
    UIElement btnClaim = UIElement.byName("Claim");

    UIElement btnAll = UIElement.byName("All");
    UIElement btnIssue = UIElement.byName("Issue");
    UIElement btnOK = UIElement.byName("OK");
    UIElement cancelledPickUp = UIElement.byXpath("//XCUIElementTypeButton[@name='Close']/preceding-sibling::XCUIElementTypeStaticText[1]");
    UIElement specialSymbol = UIElement.byXpath("//XCUIElementTypeCell[XCUIElementTypeStaticText[contains" +
            "(@name,'Items to Pick')]]/following-sibling::XCUIElementTypeCell[XCUIElementTypeStaticText[1]]");

    @Then("^I should see '(.*)' screen$")
    public void iShouldSeeScreen(String screen) throws Throwable {
        UIElement screenName = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'" + screen + "')]");
        screenName.waitFor(10);
        Assert.assertTrue(screenName.getText().contains(screen));
    }

    @Given("^I tap on '(.*)' tab and search for '(.*)' OrderID and '(.*)' it$")
    public void iSearchForOrderIDAndClaimIt(String tabName, String orderIdAlias, String action) throws Throwable {
        if (Properties.getVariable(orderIdAlias) == null)
            Assert.fail("Not able to place the order from Curbside app");
        footerTabsScreen.btnTask.waitFor(10).tap();

        switch (tabName.toLowerCase()) {
            case "all":
                iTapOnTab("All");
                break;
            case "mine":
                iTapOnTab("Mine");
                break;
            default:
                Assert.fail("This not a valid tab name");
        }


        Reporter.addStepLog("OrderID in Curbside : " + Properties.getVariable(orderIdAlias));
        String orderID = Properties.getVariable(orderIdAlias);
        UIElement orderNumber = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'" + orderID + "')]");
      //  orderNumber.scrollTo().tap();
        for (int i = 1; i < 50; i++) {
            if (orderNumber.isDisplayed()) {
                orderNumber.tap();
                break;
            } else {
                MobileDevice.swipe(180, 550, 180, 50);
            }
        }
        if (action.equalsIgnoreCase("claim")) {
            btnClaim.waitFor(5).tap();
            btnClaim.waitForNot(7);
            Steps.tapButton("Close");
        }
    }

    @And("^I tap on '(.*)' tab$")
    public void iTapOnTab(String button) throws Throwable {
        UIElement btnTasks = UIElement.byXpath("//XCUIElementTypeButton[contains(@name,'" + button + "')]");
        btnTasks.waitFor(2).tap();
    }

    @And("^I search for '(.*)' OrderID$")
    public void iSearchForSelectedOrder(String orderIdAlias) throws Throwable {
        if (Properties.getVariable(orderIdAlias) == null)
            Assert.fail("Not able to place the order from Curbside app");

        Reporter.addStepLog("OrderID in Curbside : " + Properties.getVariable(orderIdAlias));
        String orderID = Properties.getVariable(orderIdAlias);
        UIElement orderNumber = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'" + orderID + "')]");
        orderNumber.scrollTo().tap();
//        for (int i = 1; i < 50; i++) {
//            if (orderNumber.isDisplayed()) {
//                orderNumber.tap();
//                break;
//            } else {
//                MobileDevice.swipe(180, 550, 180, 50);
//            }
//        }
    }

    @Then("^I should see product details as below for (.*)$")
    public void iShouldSeeProductDetailsAsBelowForCAP(String appName) throws Throwable {
        productDetailScreen.firstProduct.tap();
        Assert.assertTrue(productDetailScreen.productName.waitFor(15).isDisplayed(),
                "Product name is not displayed");
        Assert.assertTrue(productDetailScreen.productRetailerWebsiteLink.isDisplayed());
//        Assert.assertEquals(productDetailScreen.redirectLinkPage.waitFor(10).getText(), "Product at Retailer",
//                "Link is not redirected to the desired page");
//        commonSteps.iTapOnBackButton();
        Assert.assertTrue(productDetailScreen.productSKU.isDisplayed(), "Product sku is not displayed");
        Assert.assertTrue(productDetailScreen.productDescription.isDisplayed(),
                "Product description is not displayed");
        productDetailScreen.productOverview.scrollTo();
        Assert.assertEquals(productDetailScreen.productOverview.getText(), "Overview",
                "Product overview is not displayed");
    }

    @And("^I (?:search|look) for '(.*)' OrderId with '(.*)' button")
    public void iSearchForOrderIdWith(String orderIdAlias, String btnName) throws Throwable {
        if (Properties.getVariable(orderIdAlias) == null)
            Assert.fail("Not able to place the order from Curbside app");

        String orderID = Properties.getVariable(orderIdAlias);
        footerTabsScreen.tapMyAccount();
        footerTabsScreen.tapTask();

        Assert.assertTrue(UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'" + orderID + "')]")
                .scrollTo(SwipeDirection.UP).getText().contains(orderID), "Product is not in the Mine Tab");

        Assert.assertEquals(UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'" + orderID + "')]" +
                "/following-sibling::XCUIElementTypeButton").getText(), btnName,
                "Unclaim button is not present in list");
    }

    @Given("^I mark all items as '(.*)'$")
    public void iMarkAllItemsAs(String button) throws Throwable {
        if (btnIssue.isDisplayed()) {
            int totalIssueButton = btnIssue.getCount();
            for (int i = 1; i <= totalIssueButton; i++) {
                UIElement.byXpath("//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Items to Pick')]]" +
                        "/following-sibling::XCUIElementTypeCell[" + i + "]//XCUIElementTypeButton[@name='Issue']")
                        .scrollTo(SwipeDirection.UP).tap();
                issueScreen.iTurnONItemNotAvailable("ON", button);
                Steps.tapButton("Done");
            }
            Steps.tapButton("Finish");
        }
    }

    @Then("^I should see '(.*)' orderId on Tasks screen as '(.*)'")
    public void iShouldSeeOrderIdInTaskUnder(String orderAlias, String message) throws Throwable {
        footerTabsScreen.btnTask.waitFor(7).tap();
        iTapOnTab("All");
        String orderID = Properties.getVariable(orderAlias);
//        UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'" + orderID + "')]").scrollTo().tap();
        UIElement orderNumber = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'" + orderID + "')]");
        for (int i = 1; i < 50; i++) {
            if (orderNumber.isDisplayed()) {
                orderNumber.tap();
                break;
            } else {
                MobileDevice.swipe(180, 550, 180, 50);
            }
        }
        cancelledPickUp.waitFor(10);
        Assert.assertEquals(cancelledPickUp.getText(), message, "PickUp is not Cancelled");
    }

    @And("^I checked order is ready$")
    public void iCheckOrderIsReady() throws Throwable {
        if (btnOK.isDisplayed()) {
            for (int i = 0; i < 5; i++) {
                if (btnOK.isDisplayed()) {
                    btnOK.tap();
                    Steps.tapButton("Finish");
                } else {
                    break;
                }
            }
            MobileDevice.getScreenshot(true);
        }
    }

    @And("^I search for '(.*)' Order ID and verify that '(.*)' is present$")
    public void iSearchForOrderIDAndVerifyThatHazmatSymbolIsPresent(String orderIdAlias, String symbol) throws Throwable {
        iTapOnTab("Mine");
        iTapOnTab("All");
        if (Properties.getVariable(orderIdAlias) == null)
            Assert.fail("Not able to place the order from Curbside app");

        Reporter.addStepLog("OrderID in Curbside : " + Properties.getVariable(orderIdAlias));
        String orderID = Properties.getVariable(orderIdAlias);
        UIElement iDSymbol = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'" + orderID + "')]" +
                "/following-sibling::XCUIElementTypeStaticText[1]");
        Assert.assertEquals(iDSymbol.scrollTo(SwipeDirection.UP).waitFor(3).getText(), "", "");

        iDSymbol.tap();
        Assert.assertEquals(specialSymbol.waitFor(5).getText(), "");
        btnClaim.waitFor(5).tap();
        btnClaim.waitForNot(7);
        Steps.tapButton("Close");
    }
}
