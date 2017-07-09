package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * @author kumar.anil
 * My Account- After clicking My Account tab on footer strip
 */

public class MyAccount extends AbstractScreen {
	
	static UIElement btnAccountInfo= UIElement.byAccessibilityId("Account Info");
	static UIElement btnSignIn= UIElement.byAccessibilityId("Sign In");

    static UIElement email= new UIElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]"));
    public UIElement phoneNumber= new UIElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[3]"));


    AccountInfo accountInfopage = new AccountInfo();
    SignUp signUp = new SignUp();

    @Then("^I saw email on MyAccount page$")
    public void iSawEmailOnMyAccountPage() throws Throwable {
        Assert.assertTrue(email.isDisplayed(), "Email is not displayed in Account Page");
        Assert.assertTrue(phoneNumber.isDisplayed(),"PhoneNumber is not displayed in Account Page");
    }


    @Then("^I should see my signup information under My Account$")
    public void iShouldSeeMySignupInformationUnderMyAccount() throws Throwable {
        phoneNumber.waitFor(30);
        String phoneNumberText = Properties.getVariable("signupPhoneNumber");
        String expectedNumber = phoneNumberText.substring(0, 1) + " " + "(" + phoneNumberText.substring(1, 4) + ")" + " "+ phoneNumberText.substring(4, 7) + "-" + phoneNumberText.substring(7, 11);

        Assert.assertEquals(email.getText(), Properties.getVariable("signupEmail"),
                "Entered email is not matched with Account Page");
        Assert.assertEquals(phoneNumber.getText(), expectedNumber, "Entered phoneNumber is not matched with Account Page" );

    }

    @Given("^I am not signed into application$")
    public static void ensureSignedOut() throws Throwable {
		try {
			FooterTabs.tapMyAccount();
			btnAccountInfo.tap();
			AccountInfo.btnSignOut.tap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			UIElement.byAccessibilityId("Cancel").tap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}