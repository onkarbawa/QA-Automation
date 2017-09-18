package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
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
    UIElement latestInputNeededOrder = UIElement.byXpath("//android.widget.TextView[@text='Your Input Needed']/following-sibling::android.widget.RelativeLayout[1]");
    UIElement btnCancelOrder = UIElement.byId("com.curbside.nCurbside:id/view_cancel_Pickup");
    UIElement btnCnfrmCancelOrder = UIElement.byId("com.curbside.nCurbside:id/button_submit");
    UIElement lblCancellationReason = UIElement.byId("com.curbside.nCurbside:id/text_cancellation_reason");
    UIElement btnBack = UIElement.byXpath("//android.view.ViewGroup[@resource-id='com.curbside.nCurbside:id/toolbar']/android.widget.ImageButton");
    UIElement lblOrderInfo = UIElement.byId("com.curbside.nCurbside:id/order_info");
    UIElement lblCancelledOrderID = UIElement.byId("com.curbside.nCurbside:id/text_pickup_id");
    UIElement latestCancelledOrder = UIElement.byXpath("//android.widget.TextView[@text='Cancelled']/following-sibling::android.widget.RelativeLayout[1]");
    UIElement lblTopMostOrder = UIElement.byXpath("//android.widget.ListView[@resource-id='com.curbside.nCurbside:id/list']/*/android.widget.TextView");


    String expectedOrderID;

    @And("^I cancel '(.*)' (?:order|orders)")
    public void iCancelAllOrders(String cancel) throws Throwable {
        boolean deleteAll = false;
        boolean isOrderPresent;
        footerTabsScreen.tapMyAccount();
        accountScreen.ensureAccountPage();
        Steps.tapButton("My Orders");

        MobileDevice.getScreenshot(true);

        if (cancel.equalsIgnoreCase("all"))
            deleteAll = true;

        do {
            if (!lblOrderState.waitFor(5).isDisplayed())
                break;

            if (latestInProgressOrder.isDisplayed() ||
                    latestInputNeededOrder.isDisplayed()) {
                try {
                    latestInProgressOrder.tap();
                } catch (Exception e) {
                    latestInputNeededOrder.tap();
                }

                if (cancel.equalsIgnoreCase("latest"))
                    saveOrderId();

                btnCancelOrder.scrollTo(SwipeDirection.UP);
                btnCancelOrder.tap();
                btnCnfrmCancelOrder.waitFor(5).tap();
                lblCancellationReason.waitFor(5);

                Assert.assertTrue(lblCancellationReason.isDisplayed(), "Not abe to cancel the order");
                for (int i = 0; i < 3 && !footerTabsScreen.btnMyAccount.isDisplayed(); i++) {
                    AndroidDevice.goBack();
                    Thread.sleep(3000);
                }
                footerTabsScreen.tapMyAccount();
                Steps.tapButton("My Orders");

            } else break;

            isOrderPresent = (latestInProgressOrder.isDisplayed() || latestInputNeededOrder.isDisplayed());
        } while (isOrderPresent && deleteAll);
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

    @And("^I save Order Id of the product and named as '(.*)'")
    public void iSaveOrderId(String orderID) throws Throwable {
        footerTabsScreen.tapMyAccount();
        accountScreen.ensureAccountPage();
        Steps.tapButton("My Orders");

        MobileDevice.getScreenshot(true);


        if (!lblOrderState.waitFor(5).isDisplayed())
            return;

        latestInProgressOrder.swipeUpSlow();
        latestInProgressOrder.tap();
        lblOrderInfo.scrollTo(SwipeDirection.UP);
        String orderInfo = lblOrderInfo.getText();
        Properties.setVariable(orderID, orderInfo.split(",")[0].split("\\s+")[2]);

        for (int i = 0; i < 3 && !footerTabsScreen.btnMyAccount.isDisplayed(); i++) {
            AndroidDevice.goBack();
            Thread.sleep(3000);
        }
        footerTabsScreen.tapMyAccount();

    }

    @And("^I cancel all orders")
    public void iCancelsAllOrders() throws Throwable {
        String orderState;
        footerTabsScreen.tapMyAccount();
        accountScreen.ensureAccountPage();
        Steps.tapButton("My Orders");
        MobileDevice.getScreenshot(true);


        do {
            if (!lblOrderState.isDisplayed())
                return;

            orderState = lblTopMostOrder.getText();
            switch (orderState) {
                case "Your Input Needed":
                    latestInputNeededOrder.tap();
                    Steps.tapButton("REMOVE");
                    Steps.tapButton("Proceed");
                    for (int i = 0; i < 3 && !footerTabsScreen.btnMyAccount.isDisplayed(); i++) {
                        AndroidDevice.goBack();
                        Thread.sleep(3000);
                    }
                    break;
                case "In Progress":
                    latestInProgressOrder.tap();
                    btnCancelOrder.scrollTo(SwipeDirection.UP);
                    btnCancelOrder.tap();
                    btnCnfrmCancelOrder.waitFor(5).tap();
                    lblCancellationReason.waitFor(5);
                    Assert.assertTrue(lblCancellationReason.isDisplayed(), "Not abe to cancel the order");
                    for (int i = 0; i < 3 && !footerTabsScreen.btnMyAccount.isDisplayed(); i++) {
                        AndroidDevice.goBack();
                        Thread.sleep(3000);
                    }
                    break;
                case "Cancelled":
                    return;
                default:
                    break;
            }

            footerTabsScreen.tapMyAccount();
            Steps.tapButton("My Orders");
        } while (!lblTopMostOrder.waitFor(5).getText().equalsIgnoreCase("Cancelled"));

    }

}
