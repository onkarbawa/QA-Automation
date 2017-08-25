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
 * @author kumar.anil My Account- After clicking My Account tab on footer strip
 */

public class MyAccount extends AbstractScreen {

	UIElement btnAccountInfo = UIElement.byName("Account Info");
	UIElement btnPaymentInfo = UIElement.byName("Payment Info");
	UIElement btnLoyalityCard = UIElement.byName("Loyalty Cards");

	UIElement btnSignIn = UIElement.byAccessibilityId("Sign In");

	UIElement email = new UIElement(
			By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]"));
	UIElement phoneNumber = new UIElement(
			By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[3]"));

	public MyAccount() {
		// TODO Auto-generated constructor stub
	}

	@Then("^I saw email on MyAccount page$")
	public void iSawEmailOnMyAccountPage() throws Throwable {
		email.waitFor(20);
		Assert.assertTrue(email.isDisplayed(), "Email is not displayed in Account Page");
		Assert.assertTrue(phoneNumber.isDisplayed(), "PhoneNumber is not displayed in Account Page");
	}

	@Then("^I should see my signup information under My Account$")
	public void iShouldSeeMySignupInformationUnderMyAccount() throws Throwable {
		phoneNumber.waitFor(30);
		String phoneNumberText = Properties.getVariable("signupPhoneNumber");
		String expectedNumber = phoneNumberText.substring(0, 1) + " " + "(" + phoneNumberText.substring(1, 4) + ")"
				+ " " + phoneNumberText.substring(4, 7) + "-" + phoneNumberText.substring(7, 11);

		Assert.assertEquals(email.getText(), Properties.getVariable("signupEmail"),
				"Entered email is not matched with Account Page");
		Assert.assertEquals(phoneNumber.getText(), expectedNumber,
				"Entered phoneNumber is not matched with Account Page");

	}

	@Given("^I am not signed into application$")
	public void ensureSignedOut() throws Throwable {
		homeScreen.open();
		try {
			footerTabsScreen.tapMyAccount();
			btnAccountInfo.tap();
			accountInfoScreen.signOut();
			UIElement.byXpath("//XCUIElementTypeButton[@name='Cancel']").waitFor(20).tap();
		} catch (Exception e) {
			if(UIElement.byXpath("//XCUIElementTypeButton[@name='Cancel']").isDisplayed()){
				UIElement.byXpath("//XCUIElementTypeButton[@name='Cancel']").tap();
			}
//			e.printStackTrace();
		}

		try {
			UIElement.byAccessibilityId("Navigate up").tap();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

	public void tapSignIn() throws Throwable {
		btnSignIn.tap();
	}
	@Then("^I should be logged into application$")
	public void iShouldBeLoggedIntoApplication() throws Throwable {
		String facebookEmail = Properties.getVariable("facebookEmail");
		String facebookPassword = Properties.getVariable("facebookPassword");
		email.waitFor(20);
		Assert.assertEquals(email.getText(),facebookEmail);
	}
}