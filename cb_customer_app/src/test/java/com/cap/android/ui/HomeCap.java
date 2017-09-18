package com.cap.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.SwipeDirection;
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



    @And("^I wait for Tasks to get loaded$")
    public void iWaitForTasksToLoad() throws Throwable {
        lblOrderId.waitFor(15);
        if (!lblOrderId.isDisplayed())
            lblOrderId.waitFor(10);

        Properties.setVariable("claimOrder", "7QC8C96O");
        Properties.setVariable("outOfStock", "93JMJJUB");
    }

    /**
     * @param orderIdAlias This is used to check the value stored in Properties files
     * @param tabName      All or Mine
     * @param action       claim, tap, confirm(By default)
     * @throws Throwable
     */
    @And("^I (?:search|look) for '(.*)' Order Id under '(.*)' tab and '(.*)' it$")
    public void iSearchForOrderId(String orderIdAlias, String tabName, String action) throws Throwable {
        int totalTasks ;
        int startingTask = 0;
        UIElement lblOrderId;
        UIElement btnClaim;

        Reporter.addStepLog("OrderID in Curbside : " + Properties.getVariable(orderIdAlias));

        tabName = tabName.toLowerCase();

        switch (tabName) {
            case "all":
                btnAllTasks.tap();
                totalTasks = Integer.parseInt(lblTotalTasks.getText().split("\\s")[0]);
                if (totalTasks > 18)
                    startingTask = totalTasks - 17;
                UIElement nthTask = UIElement.byXpath("//android.widget.RelativeLayout[@index='" + startingTask + "']");
                nthTask.swipeUpSlow();
                break;
            case "mine":
                btnMineTasks.tap();
                footerTabsCap.btnTasks.tap();
                break;
            default:
                Assert.fail("Please enter correct 'Tab name'");
                break;
        }

        lblOrderId = UIElement.byXpath("//android.widget.TextView[contains(@text,'" + Properties.getVariable(orderIdAlias) + "')]");
        lblOrderId.swipeUpSlow();
        Assert.assertTrue(lblOrderId.isDisplayed(), orderIdAlias + " order is not present");
        MobileDevice.getScreenshot(true);

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
}
