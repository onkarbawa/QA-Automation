package com.curbside.android.ui;

import com.curbside.automation.uifactory.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 23/08/17.
 */
public class MyOrders extends AbstractScreen {

    UIElement lblOrderState = UIElement.byId("com.curbside.nCurbside:id/heading_view");
    UIElement latestInProgressOrder = UIElement.byXpath("//android.widget.TextView[@text='In Progress']/following-sibling::android.widget.RelativeLayout[1]");
    UIElement btnCancelOrder = UIElement.byId("com.curbside.nCurbside:id/view_cancel_Pickup");
    UIElement btnCnfrmCancelOrder = UIElement.byId("com.curbside.nCurbside:id/button_submit");
    UIElement lblCancellationReason = UIElement.byId("com.curbside.nCurbside:id/text_cancellation_reason");
    UIElement btnBack = UIElement.byXpath("//android.view.ViewGroup[@resource-id='com.curbside.nCurbside:id/toolbar']/android.widget.ImageButton");
    UIElement lblOrderInfo = UIElement.byId("com.curbside.nCurbside:id/order_info");
    UIElement lblCancelledOrderID = UIElement.byId("com.curbside.nCurbside:id/text_pickup_id");
    UIElement latestCancelledOrder = UIElement.byXpath("//android.widget.TextView[@text='Cancelled']/following-sibling::android.widget.RelativeLayout[1]");


    String expectedOrderID;

    @And("^I cancel '(.*)' (?:order|orders)")
    public void iCancelAllOrders(String cancel) throws Throwable {
        boolean deleteAll = false;
        boolean orderPresent;
        footerTabsScreen.tapMyAccount();
        accountScreen.ensureAccountPage();
        Steps.tapButton("My Orders");

        MobileDevice.getScreenshot(true);

        if (cancel.equalsIgnoreCase("all"))
            deleteAll = true;

        do {
            if (lblOrderState.waitFor(5).getText().equalsIgnoreCase("In Progress")) {
                latestInProgressOrder.tap();

                if (cancel.equalsIgnoreCase("latest"))
                    saveOrderId();

                btnCancelOrder.scrollTo(SwipeDirection.UP);
                btnCancelOrder.tap();
                btnCnfrmCancelOrder.waitFor(5).tap();
                lblCancellationReason.waitFor(5);

                Assert.assertTrue(lblCancellationReason.isDisplayed(), "Not abe to cancel the order");
                for (int i = 0; i < 10; i++) {
                    try {
                        btnBack.waitFor(3).tap();
                    } catch (Exception e) {
                    }
                }
                footerTabsScreen.tapMyAccount();
                Steps.tapButton("My Orders");

            } else break;

            orderPresent = lblOrderState.waitFor(5).getText().equalsIgnoreCase("In Progress");
        } while (orderPresent && deleteAll);
        MobileDevice.getScreenshot(true);
    }


    public void saveOrderId() throws Throwable {
        lblOrderInfo.scrollTo(SwipeDirection.UP);
        String OrderInfo = lblOrderInfo.getText();
        expectedOrderID = OrderInfo.split(",")[0].split("\\s+")[2];
    }

    @Then("^I should see the order under cancelled tab")
    public void assertCancelledOrder() throws Throwable {
        latestCancelledOrder.swipeUpSlow();
        latestCancelledOrder.tap();
        MobileDevice.getScreenshot(true);
        Assert.assertEquals(expectedOrderID, lblCancelledOrderID.getText(), "Cancelled order is not showing under cancelled section");
    }
}
