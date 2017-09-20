package com.cap.android.ui;

import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by hitesh.grover on 19/09/17.
 */
public class More extends AbstractScreenCap {

    UIElement btnBack = UIElement.byId("com.curbside.nCap:id/img");
    UIElement btnCancelledPickups = UIElement.byId("com.curbside.nCap:id/tvCancelledPickups");

    @And("^I go to Cancelled pickups screen$")
    public void iGoToCancelledPickups() throws Throwable {
        Steps.tapButton("More");
        try {
            btnBack.waitFor(1).tap();
        } catch (Exception e) {
        }
        btnCancelledPickups.waitFor(1).tap();
    }
}
