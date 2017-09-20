package com.cap.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by hitesh.grover on 05/09/17.
 */
public class Issue extends AbstractScreenCap {

    UIElement btnItemNotAvail = UIElement.byId("com.curbside.nCap:id/swItemsNotAvailable");
    UIElement btnDone = UIElement.byId("com.curbside.nCap:id/tvDone");

    @And("^I tap on Items not available toggle button$")
    public void iTapItemNotAvail() throws Throwable {
        btnItemNotAvail.waitFor(1).tap();
    }

}
