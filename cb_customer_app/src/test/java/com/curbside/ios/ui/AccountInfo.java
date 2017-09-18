package com.curbside.ios.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 06/07/17.
 */
public class AccountInfo extends AbstractScreen {

	UIElement txtFirstName = UIElement.byXpath("//*[@label='First Name']/following-sibling::*");
	UIElement txtLastName = UIElement.byXpath("//*[@label='Last Name']/following-sibling::*");
	UIElement txtEmail = UIElement.byXpath("//*[@label='Email']/following-sibling::*");
	UIElement txtMobile = UIElement.byXpath("//*[@label='Mobile']/following-sibling::*");
	UIElement txtPassword = UIElement.byXpath("//*[@label='Password']/following-sibling::*");

	UIElement btnSignOut = UIElement.byName("Sign Out");
	UIElement btnBack = UIElement.byAccessibilityId("Back");
	UIElement btnAccount = UIElement.byAccessibilityId("Account");
	UIElement btnEdit = UIElement.byAccessibilityId("Edit");

	public AccountInfo() {
	}
	
	@Then("^I should see my signup information under Account Info$")
	public void verifySignUpInfo() throws Throwable {
		try {
			Thread.sleep(20000);
			footerTabsScreen.tapMyAccount();
		}catch (Exception e){}
		myAccountScreen.btnAccountInfo.waitFor(20).tap();

		String phoneNumber = Properties.getVariable("signupPhoneNumber");
		String email = Properties.getVariable("signupEmail");
		String password = Properties.getVariable("signupPassword");

		String actEmail = txtEmail.getText();
		String actPhoneNumber = txtMobile.getText();
		String actPassword = txtPassword.getText();

		Reporter.addStepLog(String.format("Email: actual- %s, expected- %s", actEmail, email));
		Reporter.addStepLog(String.format("Phone: actual- %s, expected- %s", actPhoneNumber, phoneNumber));
		Reporter.addStepLog(String.format("Password: actual- %s, expected- %s", actPassword, password));

		phoneNumber = StringUtils.right(phoneNumber, 10);
		phoneNumber = String.format("1 (%s) %s-%s", phoneNumber.substring(0, 3), phoneNumber.substring(3, 6), phoneNumber.substring(6, 10));


		Assert.assertEquals(actEmail, email);
		Assert.assertEquals(actPhoneNumber, phoneNumber);
		Assert.assertEquals(actPassword, StringUtils.repeat("∙", 12));
	}

	public void signOut() throws Throwable {
		btnSignOut.tap();
		try {
			UIElement.byName(" Sign out").waitFor(7).tap();
			MobileDevice.getScreenshot(true);
		}catch (Exception e){}
	}
}
