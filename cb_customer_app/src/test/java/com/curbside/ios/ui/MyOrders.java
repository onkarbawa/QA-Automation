package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.SwipeDirection;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * Created by bawa.onkar
 */

public class MyOrders extends AbstractScreen {

    UIElement orderInProgress = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Order In Progress')]");
    UIElement myOrderTitle = UIElement.byName("My Orders");
    UIElement orderID = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[10]//XCUIElementTypeStaticText");
    UIElement latestOrderCancel = UIElement.byXpath("//XCUIElementTypeOther[XCUIElementTypeStaticText[@name='Cancelled']]/following-sibling::XCUIElementTypeCell[1]");


    @And("^I tap on Order In Progress$")
    public void iTapOnOrderInProgress() throws Throwable {
        orderInProgress.waitFor(5).tap();
        MobileDevice.getSource(true);
    }

    @When("^I tap on reason '(.*)' for cancelling$")
    public void iTapOnReasonForCancelling(String reason) throws Throwable {
        UIElement.byXpath("//XCUIElementTypeStaticText[@name='"+reason+"']").tap();
    }

    @And("^I store the value of OrderId$")
    public void iStoreTheValueOfOrderId() throws Throwable {
        String orderNo;
        if (orderID.isDisplayed()){
            orderNo = orderID.getText();
            orderNo = orderNo.split("\\s")[1];
        }else {
            orderID.scrollTo(SwipeDirection.UP);
            orderNo = orderID.getText();
            orderNo = orderNo.split("\\s")[1];
        }
        Properties.setVariable("orderID",orderNo);
    }

    @Then("^I should see cancel order in My Orders Screen$")
    public void iShouldSeeCancelOrderInMyOrdersScreen() throws Throwable {
        footerTabsScreen.btnMyAccount.waitFor(3).tap();
        Steps.tapButton("My Orders");
        MobileDevice.getSource(true);
        Assert.assertEquals(myOrderTitle.getText(),"My Orders");
        MobileDevice.getSource(true);
        latestOrderCancel.tap();
        cancelledOrderScreen.iShouldSeeInformationUnderCancelledOrderScreen();
    }
}
