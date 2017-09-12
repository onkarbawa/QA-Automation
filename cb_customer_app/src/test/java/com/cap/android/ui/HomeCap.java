package com.cap.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
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


    String noOfTasks;

    @And("^I wait for Tasks to get loaded$")
    public void iWaitForTasksToLoad() throws Throwable {
        lblOrderId.waitFor(15);
        if (!lblOrderId.isDisplayed())
            lblOrderId.waitFor(10);
    }

    @And("^I search for Order Id named as '(.*)' and claim it$")
    public void iSearchForOrderId(String orderIdName) throws Throwable {
        Properties.setVariable("outOfStock", "NUQXNSCD");
        orderIdName = "outOfStock";
        int totalTasks;
        int startingTask;
        boolean orderFound = false;
        noOfTasks = lblTotalTasks.getText().split("\\s")[0];
        totalTasks = Integer.parseInt(noOfTasks);

        if( totalTasks > 7)
            startingTask = totalTasks - 6;
        else
            startingTask = 0;

        while(startingTask < totalTasks) {
            UIElement nthTask = UIElement.byXpath("//android.widget.TextView[@resource-id='com.curbside.nCap:id/tvTitle']" +
                    "/../parent::android.widget.RelativeLayout[@index='"+startingTask+"']");
            nthTask.swipeUpSlow();

            List<WebElement> listOfTasks = tasks.getElements();
            for (WebElement task : listOfTasks) {
                String orderID = task.findElement(By.id("com.curbside.nCap:id/tvOrderId")).getText();
                if (orderID.contains(Properties.getVariable(orderIdName))) {

                    try{
                        MobileDevice.getScreenshot(true);
                        task.findElement(By.id("com.curbside.nCap:id/bClaimTask")).click();
                        MobileDevice.getScreenshot(true);
                    }catch (Exception e)
                    {
                        MobileDevice.getScreenshot(true);
                        task.click();
                        MobileDevice.getScreenshot(true);
                    }
                    orderFound = true;
                    break;
                }
            }
            startingTask = startingTask + 2;
            if (startingTask == totalTasks)
                startingTask = totalTasks-1;
        }

        Assert.assertTrue(orderFound, "Not able to find the Order in the list");
    }

    @And("^I tap on Mine tab$")
    public void iTapOnMineTab() throws Throwable {
        btnMineTasks.waitFor(2).tap();
    }

    @And("^I search for Order Id named as '(.*)' and tap it$")
    public void iSearchOrderMineTab(String orderIdName) throws Throwable {

        List<WebElement> listOfTasks = tasks.getElements();

        for (WebElement task : listOfTasks) {
            WebElement lblOrderID = task.findElement(By.id("com.curbside.nCap:id/tvOrderId"));
            String orderID = lblOrderID.getText();

            if (orderID.contains(Properties.getVariable(orderIdName))) {
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
