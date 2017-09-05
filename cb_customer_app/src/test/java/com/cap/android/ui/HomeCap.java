package com.cap.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.SwipeDirection;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by hitesh.grover on 05/09/17.
 */
public class HomeCap extends AbstractScreenCap {

    UIElement lblOrderId = UIElement.byId("com.curbside.nCap:id/tvOrderId");
    UIElement lblOrderTitle = UIElement.byId("com.curbside.nCap:id/tvTitle");
    UIElement btnClaim = UIElement.byId("com.curbside.nCap:id/bClaimTask");
    UIElement lblTotalTasks = UIElement.byId("com.curbside.nCap:id/toolbar_title");
    UIElement tasks = UIElement.byXpath("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout");
    UIElement btnMineTasks = UIElement.byId("com.curbside.nCap:id/rbMine");


    String noOfTasks;

    @And("^I wait for Tasks screen to get loaded$")
    public void iWaitForScreenLoaded() throws Throwable {
        lblOrderId.waitFor(15);
        if (!lblOrderId.isDisplayed())
            lblOrderId.waitFor(10);
    }

    @And("^I search for Order Id named as '(.*)' and claim it$")
    public void iSearchForOrderId(String orderIdName) throws Throwable {
        int totalTasks;
        int startingTask;
        noOfTasks = lblTotalTasks.getText().split("\\s")[0];
        System.out.println("noOFTASKS--"+noOfTasks);
        totalTasks = Integer.parseInt(noOfTasks);

        if( totalTasks > 7)
            startingTask = totalTasks - 6;
        else
            startingTask = 0;

        while(startingTask < totalTasks) {

            UIElement nthTask = UIElement.byXpath("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout["+startingTask+"]");
            nthTask.swipeUpSlow();

            List<WebElement> listOfTasks = tasks.getElements();

            for (WebElement task : listOfTasks) {
                String orderID = task.findElement(By.id("com.curbside.nCap:id/tvOrderId")).getText();
                System.out.println("CAPordrid task--"+orderID);
                System.out.println("propertiesGet--"+Properties.getVariable(orderIdName));
                if (orderID.contains(Properties.getVariable(orderIdName))) {
                    task.findElement(By.id("com.curbside.nCap:id/bClaimTask")).click();
                    break;
                }
            }
            startingTask = startingTask + 2;
        }


    }

    @And("^I tap on Mine tab$")
    public void iTapOnMineTab() throws Throwable {
        btnMineTasks.waitFor(2).tap();
    }

    @And("^I search for Order Id named as '(.*)' and tap it$")
    public void iSearchOrderMineTab(String orderIdName) throws Throwable {

        List<WebElement> listOfTasks = tasks.getElements();

        for (WebElement task : listOfTasks) {
            System.out.println("--inwebelement--");
            WebElement lblOrderID = task.findElement(By.id("com.curbside.nCap:id/tvOrderId"));
            String orderID = lblOrderID.getText();

            if (orderID.contains(Properties.getVariable(orderIdName))) {
                lblOrderID.click();
                break;
            }
        }
    }
}
