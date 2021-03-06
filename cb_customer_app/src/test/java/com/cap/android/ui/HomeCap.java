package com.cap.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by hitesh.grover on 05/09/17.
 */
public class HomeCap extends AbstractScreenCap {

    UIElement lblOrderId = UIElement.byId("com.curbside.nCap:id/tvOrderId");
    UIElement lblOrderTitle = UIElement.byId("com.curbside.nCap:id/tvTitle");
    UIElement btnClaim = UIElement.byId("com.curbside.nCap:id/bClaimTask");
    UIElement lblTotalTasks = UIElement.byId("com.curbside.nCap:id/toolbar_title");
    UIElement tasks = UIElement.byXpath("//android.widget.TextView[@resource-id='com.curbside.nCap:id/tvTitle']" +
            "/../parent::android.widget.RelativeLayout");
    UIElement btnMineTasks = UIElement.byId("com.curbside.nCap:id/rbMine");
    UIElement btnAllTasks = UIElement.byId("com.curbside.nCap:id/rbAll");
    UIElement lblOrderDetailScreenTitle = UIElement.byId("com.curbside.nCap:id/toolbarTitle");


    @And("^I wait for Tasks to get loaded$")
    public void iWaitForTasksToLoad() throws Throwable {
        lblOrderId.waitFor(15);
        if (!lblOrderId.isDisplayed())
            lblOrderId.waitFor(10);
    }

    /**
     * @param orderIdAlias This is used to check the value stored in Properties files
     * @param tabName      All or Mine
     * @param action       claim, tap, confirm(By default)
     * @throws Throwable
     */
    @And("^I (?:search|look) for '(.*)' Order Id under '(.*)' tab and '(.*)' it$")
    public void iSearchForOrderId(String orderIdAlias, String tabName, String action) throws Throwable {
        footerTabsCap.btnTasks.tap();
        try {
            if (Properties.getVariable(orderIdAlias) == null)
                Assert.fail("Not able to place the order from Curbside app");
        } catch (NullPointerException e) {
            Assert.fail("Not able to store the Order ID in Properties class");
        }
        int totalTasks;
        int startingTask = 0;
        UIElement lblOrderId;
        UIElement btnClaim;
        tabName = tabName.toLowerCase();
        Reporter.addStepLog("OrderID in Curbside : " + Properties.getVariable(orderIdAlias));

        switch (tabName) {
            case "all":
                btnAllTasks.waitFor(1).tap();
                totalTasks = Integer.parseInt(lblTotalTasks.getText().split("\\s")[0]);
                if (totalTasks > 30)
                    startingTask = totalTasks / 2;
                UIElement nthTask = UIElement.byXpath("//android.widget.RelativeLayout[@index='" + startingTask + "']");
                nthTask.swipeUpSlow();
                break;
            case "mine":
                btnMineTasks.waitFor(1).tap();
                footerTabsCap.btnTasks.tap();
                break;
            default:
                Assert.fail("Please enter correct 'Tab name'");
                break;
        }

        lblOrderId = UIElement.byXpath("//android.widget.TextView[contains(@text,'" + Properties.getVariable(orderIdAlias) + "')]");
        lblOrderId.swipeUpSlow();
        Assert.assertTrue(lblOrderId.isDisplayed(), orderIdAlias + " order is not present");

        if (action.equalsIgnoreCase("claim")) {
            btnClaim = UIElement.byXpath("//android.widget.TextView[contains(@text,'" + Properties.getVariable(orderIdAlias) + "')]" +
                    "/../android.widget.Button");
            btnClaim.tap();
            btnClaim.waitFor(4);
            Assert.assertEquals(btnClaim.getText(), "Unclaim",
                    "Text doesn't change to 'Unclaim' after pressing 'Claim' button");
            MobileDevice.getScreenshot(true);

        } else if (action.equalsIgnoreCase("tap")) {
            lblOrderId.tap();
            MobileDevice.getScreenshot(true);
        } else if (action.equalsIgnoreCase("confirm")) {
            Assert.assertTrue(lblOrderId.isDisplayed(), orderIdAlias + " Order is not present after confirming it again");
        }
    }

    @And("^I tap on Mine tab$")
    public void iTapOnMineTab() throws Throwable {
        btnMineTasks.waitFor(2).tap();
    }

    @And("^I search for Order Id named as '(.*)' and tap it$")
    public void iSearchOrderMineTab(String orderAlias) throws Throwable {
        List<WebElement> listOfTasks = tasks.getElements();
        for (WebElement task : listOfTasks) {
            WebElement lblOrderID = task.findElement(By.id("com.curbside.nCap:id/tvOrderId"));
            String orderID = lblOrderID.getText();

            if (orderID.contains(Properties.getVariable(orderAlias))) {
                lblOrderID.click();
                break;
            }
        }
    }

    @Then("^I should see Home screen of CAP$")
    public void iShouldSeeHomeScreen() throws Throwable {
        this.iWaitForTasksToLoad();
        Assert.assertTrue(btnMineTasks.isDisplayed(), "Home Screen not visible");
    }

    @Then("^I should see order title as '(.*)'$")
    public void iShouldSeeOrderHeading(String expectedTitle) throws Throwable {
        lblOrderDetailScreenTitle.waitFor(3);
        Assert.assertEquals(lblOrderDetailScreenTitle.getText(), expectedTitle, "Title on the Order Detail screen is not same");
    }

    @Then("^I should see '(.*)' symbol inside the order$")
    public void iShouldSeeSymbol(String symbol) throws Throwable {
        UIElement symbolImg = UIElement.byId("com.curbside.nCap:id/imgPickupRestrictions");
        Assert.assertTrue(symbolImg.isDisplayed(), symbol + " symbol is not present");
    }
}
