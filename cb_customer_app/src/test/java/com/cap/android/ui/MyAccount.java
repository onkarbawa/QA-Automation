package com.cap.android.ui;

import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

/**
 * Created by hitesh.grover on 12/09/17.
 */
public class MyAccount extends AbstractScreenCap{

    UIElement btnSignOut = UIElement.byUISelector("new UiSelector().text(\"Sign Out\")");
    UIElement btnSignOutPopUp = UIElement.byId("android:id/button1");


    @When("^I Sign out from CAP$")
    public void iSignOutCap() throws Throwable {
        Steps.tapButton("My Account");
        btnSignOut.waitFor(2).tap();
        if (btnSignOutPopUp.waitFor(1).isDisplayed())
            btnSignOutPopUp.tap();
    }
}
