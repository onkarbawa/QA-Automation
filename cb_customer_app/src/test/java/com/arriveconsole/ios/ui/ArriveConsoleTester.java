package com.arriveconsole.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.Helpers;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;

/**
 * Created by bawa.onkar.
 */
public class ArriveConsoleTester extends AbstractScreen {
    UIElement txtBxName = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Name :']/following-sibling::" +
            "XCUIElementTypeTextField[1]");
    UIElement txtBxTracking = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Tracking ID :']/following-sibling::" +
            "XCUIElementTypeTextField[1]");
    UIElement txtBxToken = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Token :']/following-sibling::" +
            "XCUIElementTypeTextField[1]");
    UIElement txtBxSiteId = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Site ID :']/following-sibling::" +
            "XCUIElementTypeTextField[1]");

    public void iStartSampleTrip(int noOfTrips) throws Throwable {
        for (int i = 0; i < noOfTrips; i++) {
            commonSteps.launchApplicationClean("ARRIVEConsoleTester", "first");
            welcomeScreen.iAcceptNotificationsAlertMessage();
            String randomText = "iOSTest" + Helpers.getRandomFirstName();
            arriveConsoleTesterScreen.txtBxName.sendKeys(randomText, false);
            arriveConsoleTesterScreen.txtBxTracking.sendKeys(randomText, false);
            arriveConsoleTesterScreen.txtBxToken.sendKeys(randomText, false);
            arriveConsoleTesterScreen.txtBxSiteId.sendKeys(Properties.getVariable("selectedSite"),
                    false);
            Steps.tapButton("Start Tracking");
        }
    }
}
