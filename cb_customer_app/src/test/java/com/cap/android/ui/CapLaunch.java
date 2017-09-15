package com.cap.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 04/09/17.
 */
public class CapLaunch extends AbstractScreenCap{

    UIElement fieldAccountName = UIElement.byId("com.curbside.nCap:id/etAccountName");
    UIElement fieldUserName = UIElement.byId("com.curbside.nCap:id/etUsername");
    UIElement fieldPassword = UIElement.byId("com.curbside.nCap:id/etPassword");
    UIElement btnVersionNumber = UIElement.byId("com.curbside.nCap:id/tvReleaseVersion");
    UIElement fieldStoreId = UIElement.byId("com.curbside.nCap:id/etStoreID");
    UIElement btnStaging = UIElement.byId("com.curbside.nCap:id/rbStaging");
    UIElement btnOK = UIElement.byId("com.curbside.nCap:id/bOK");
    UIElement btnpopUpOK = UIElement.byId("android:id/button1");
    UIElement lblLoginErrorMsg = UIElement.byId("com.curbside.nCap:id/tvAuthorizationFailed");
    UIElement lblReleaseVersion = UIElement.byId("com.curbside.nCap:id/tvReleaseVersion");

    @And("^I enter \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" for login$")
    public void iEnterCredentials(String accountName, String userName, String password) throws Throwable {
        fieldAccountName.waitFor(5);
        fieldAccountName.sendKeys(accountName, true);
        fieldUserName.sendKeys(userName, true);
        fieldPassword.clearText();
        fieldPassword.sendKeys(password, true);
    }

    @And("^I have selected test environment for CAP$")
    public void iSelectCapEnv() throws Throwable {
        commonSteps.acceptLocationAlert();

        if( footerTabsCap.btnTasks.isDisplayed() || lblReleaseVersion.getText().contains(".s EE (cvs_9945)"))
            return;

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

    @And("^I should see this \"([^\"]*)\" on the screen$")
    public void iShouldSeeErrorMsg(String expectedErrorMsg) throws Throwable {
        String actualErrorMsg = lblLoginErrorMsg.waitFor(2).getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Got different error message");
    }

    @Then("^I should see Login Screen of CAP$")
    public void iShouldSeeLoginScreenCap() throws Throwable {
        Assert.assertTrue(fieldAccountName.waitFor(1).isDisplayed(), "Not able to Signout from CAP");
    }

    @And("^I am logged in to the CAP with \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void mustBeLoggedIn(String accountName, String userName, String password) throws Throwable {

        if( footerTabsCap.btnTasks.isDisplayed())
            myAccount.iSignOutCap();

        this.iEnterCredentials(accountName, userName, password);
        Steps.tapButton("Login");
    }
}
