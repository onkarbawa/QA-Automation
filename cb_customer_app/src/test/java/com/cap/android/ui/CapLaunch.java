package com.cap.android.ui;

import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by hitesh.grover on 04/09/17.
 */
public class CapLaunch {

    UIElement fieldAccountName = UIElement.byId("com.curbside.nCap:id/etAccountName");
    UIElement fieldUserName = UIElement.byId("com.curbside.nCap:id/etUsername");
    UIElement fieldPassword = UIElement.byId("com.curbside.nCap:id/etPassword");
    UIElement btnVersionNumber = UIElement.byId("com.curbside.nCap:id/tvReleaseVersion");
    UIElement fieldStoreId = UIElement.byId("com.curbside.nCap:id/etStoreID");
    UIElement btnStaging = UIElement.byId("com.curbside.nCap:id/rbStaging");
    UIElement btnOK = UIElement.byId("com.curbside.nCap:id/bOK");
    UIElement btnpopUpOK = UIElement.byId("android:id/button1");

    @And("^I enter \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" for login$")
    public void iEnterCredentials(String accountName, String userName, String password) throws Throwable {
        fieldAccountName.waitFor(5);
        fieldAccountName.sendKeys(accountName, true);
        fieldUserName.sendKeys(userName, true);
        fieldPassword.sendKeys(password, true);
    }

    @And("^I have selected test environment for CAP$")
    public void iSelectCapEnv() throws Throwable {
        for (int i = 0; i < 4; i++) {
            btnVersionNumber.tap();
        }
        btnVersionNumber.longPress(4);
        btnStaging.waitFor(2).tap();
        fieldStoreId.sendKeys("cvs_9945", true);
        btnOK.tap();
        btnpopUpOK.tap();
        DriverFactory.closeApp();
        DriverFactory.launchApp();
    }
}
