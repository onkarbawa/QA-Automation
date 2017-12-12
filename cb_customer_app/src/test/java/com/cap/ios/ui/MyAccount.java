package com.cap.ios.ui;

/**
 * Created by bawa.onkar
 */
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.Given;

public class MyAccount extends AbstractScreen {

    UIElement btnSignOut = UIElement.byName("Sign Out");
    UIElement btnSignOutConfirm = UIElement.byXpath("//XCUIElementTypeStaticText[@name='You have tasks that have not " +
            "been completed']/following-sibling::XCUIElementTypeButton[@name='Sign Out']");

    @Given("^I am not signed into application cap$")
    public void ensureSignedOutCap() throws Throwable {
        if(loginScreen.accountName.isDisplayed())
            return;
        try {
            footerTabsScreen.tapMyAccount();
            btnSignOut.tap();
            try {
                btnSignOutConfirm.waitFor(3).tap();
            }catch (Exception e){}
        } catch (Exception e) {}
    }
}
