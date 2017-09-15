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


    String noOfTasks;
    String orderID;

    @And("^I wait for Tasks to get loaded$")
    public void iWaitForTasksToLoad() throws Throwable {
        lblOrderId.waitFor(15);
        if (!lblOrderId.isDisplayed())
            lblOrderId.waitFor(10);
    }

    /**
     * @param orderAlias This is used to check the value stored in Properties files
     * @param tabName    All or Mine
     * @param action     claim, tap, confirm(By default)
     * @throws Throwable
     */
    @And("^I (?:search|look) for '(.*)' Order Id under '(.*)' tab and '(.*)' it$")
    public void iSearchForOrderId(String orderAlias, String tabName, String action) throws Throwable {
        int totalTasks = 0;
        int startingTask = 0;
        boolean orderFound = false;

        Reporter.addStepLog("OrderID in Curbside : "+Properties.getVariable(orderAlias));

        if (tabName.equalsIgnoreCase("All")) {
            btnAllTasks.tap();
            noOfTasks = lblTotalTasks.getText().split("\\s")[0];
            totalTasks = Integer.parseInt(noOfTasks);
            if (totalTasks > 7)
                startingTask = totalTasks - 6;
        } else if (tabName.equalsIgnoreCase("Mine")) {
            btnMineTasks.tap();
            noOfTasks = btnMineTasks.getText();
            noOfTasks = noOfTasks.substring(noOfTasks.indexOf("(") + 1, noOfTasks.lastIndexOf(")"));
            totalTasks = Integer.parseInt(noOfTasks);
            footerTabsCap.btnTasks.tap();
        }

        while (startingTask < totalTasks) {
            UIElement nthTask = UIElement.byXpath("//android.widget.TextView[@resource-id='com.curbside.nCap:id/tvOrderId']" +
                    "/../parent::android.widget.RelativeLayout[@index='" + startingTask + "']");
            nthTask.swipeUpSlow();
            List<WebElement> listOfTasks = tasks.getElements();

            for (WebElement task : listOfTasks) {
                try {
                    orderID = task.findElement(By.id("com.curbside.nCap:id/tvOrderId")).getText();
                } catch (Exception e) {
                    orderID = "ElementNotVisibleYet";
                }

                if (orderID.contains(Properties.getVariable(orderAlias))) {
                    Reporter.addStepLog("OrderID in CAP : "+orderID);
                    if (action.equalsIgnoreCase("claim")) {
                        MobileDevice.getScreenshot(true);
                        WebElement btnClaim = task.findElement(By.id("com.curbside.nCap:id/bClaimTask"));
                        btnClaim.click();
                        Thread.sleep(4000);
                        Assert.assertEquals(btnClaim.getText(), "Unclaim",
                                "Text doesn't change to 'Unclaim' after pressing 'Claim' button");

                    } else if (action.equalsIgnoreCase("tap")) {
                        MobileDevice.getScreenshot(true);
                        task.click();
                    }
                    orderFound = true;
                    break;
                }
            }

            if (orderFound)
                break;

            startingTask = startingTask + 2;

            if (startingTask == totalTasks) {
                startingTask = totalTasks - 1;
                MobileDevice.swipe(SwipeDirection.UP);
            }

            if (startingTask == (totalTasks - 1)) {
                MobileDevice.swipe(SwipeDirection.UP);
            }
        }
        Assert.assertTrue(orderFound, "Not able to find the Order in the list");
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
