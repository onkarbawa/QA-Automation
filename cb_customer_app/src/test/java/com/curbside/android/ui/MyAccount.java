package com.curbside.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * @author hitesh.grover My Account- After clicking My Account tab on footer
 *         strip
 */

public class MyAccount extends AbstractScreen {

	UIElement signUp = new UIElement(By.id("com.curbside.nCurbside:id/button_sign_up"));

	UIElement paymentInfo = new UIElement(
			By.xpath("//android.widget.ListView/android.widget.RelativeLayout[@index='3']"));
	// static UIElement paymentInfoID= new
	// UIElement(By.id("com.curbside.nCurbside:id/textview_my_account_listitem"));
	UIElement signInButton = new UIElement(By.id("com.curbside.nCurbside:id/button_sign_in"));

	UIElement viewEmailId = new UIElement(By.id("com.curbside.nCurbside:id/text_email"));
	UIElement viewUserName = new UIElement(By.id("com.curbside.nCurbside:id/text_name"));
	UIElement viewUserPhoneNumber = UIElement.byId("com.curbside.nCurbside:id/text_phone_number");

	UIElement btnNavigateUp = UIElement.byAccessibilityId("Navigate up");

	@And("^I tap on PaymentInfo button on Account page$")
	public void iTapOnPaymentInfoButtonOnAccountPage() throws Throwable {
		paymentInfo.waitFor(3).tap();
		paymentInfo.waitForNot(3);
		// Retry - In some devices it doesn't click 1st time
		if (paymentInfo.isDisplayed())
			paymentInfo.tap();
	}

	@And("^I tap on sign in button on Account page$")
	public void iTapOnSignInButtonOnAccountPage() throws Throwable {
		signInButton.waitFor(5).tap();
	}

	@Then("^I should see my given information under Account Info$")
	public void iShouldSeeTheAccountDetailsSameAsProvidedOneS() throws Throwable {
		String phoneNumber = Properties.getVariable("signupPhoneNumber");
		String email = Properties.getVariable("signupEmail");

		viewEmailId.waitFor(10);
		Assert.assertTrue(viewEmailId.isDisplayed(), "User is not able to sign-in");

		String actEmail = viewEmailId.getText();
		String actPhoneNumber = viewUserPhoneNumber.getText().split("\\(")[1].replaceAll("[)-]", "").replaceAll(" ",
				"");
		String actPhoneNumberAndroid6 = viewUserPhoneNumber.getText().replaceAll("[()-]", "").replaceAll(" ", "");
		Reporter.addStepLog(String.format("Email: actual- %s, expected- %s", actEmail, email));
		Reporter.addStepLog(String.format("Phone: actual- %s, expected- %s", actPhoneNumber, phoneNumber));
		Reporter.addStepLog(String.format("Phone: actual- %s, expected- %s", actPhoneNumberAndroid6, phoneNumber));

		Assert.assertTrue(phoneNumber.equals(actPhoneNumber) || phoneNumber.equals(actPhoneNumberAndroid6),
				"Phone number doesn't match");
		Assert.assertEquals(actEmail, email, "email address doesn't match");

	}

	@Then("^I should see email as '(.*)' on Account Info page$")
	public void verifyEmail(String in) throws Throwable {
		Assert.assertEquals(viewEmailId.getText(), in, "Email doesn't match");
	}

	@Then("^I should see name as '(.*)' on Account Info page$")
	public void verifyName(String in) throws Throwable {
		Assert.assertEquals(viewUserName.getText(), in, "User name doesn't match");
	}

	@Then("^I should see phone number as '(.*)' on Account Info page$")
	public void verifyPhoneNumber(String in) throws Throwable {
		Assert.assertEquals(viewUserPhoneNumber.getText(), in, "Phone number doesn't match");
	}

	@And("^I tap on Sign up button on My Account page$")
	public void iTapOnSignUpButtonOnMyAccountPage() throws Throwable {
		if (!signUp.isDisplayed()) {
			footerTabsScreen.tapMyAccount();
		}
		signUp.waitFor(5).tap();
	}

	@Given("^I am not signed into application$")
	public void ensureSignedOut() throws Throwable {
		try {
		    if(!footerTabsScreen.btnMyAccount.isDisplayed())
		        homeScreen.open();
			footerTabsScreen.tapMyAccount();
			ensureAccountPage();
			if(viewEmailId.waitFor(3).isDisplayed()){
				Steps.tapButton("Account Info");
				Steps.tapButton("Sign Out");
				//After error pop up is there do not delete this code
				Steps.tapButton("Sign Out");
				signUp.waitFor(3);
			}

		} catch (Exception e) {
		}

		try {
			btnNavigateUp.tap();
		} catch (Exception e) {
		}

		MobileDevice.getScreenshot(true);

	}

	@And("^I ensure that I am on Account page$")
	public void ensureAccountPage() throws Throwable {
		if (!paymentInfo.isDisplayed()) {
			footerTabsScreen.tapMyAccount();
		}
	}

	@Then("^I should be logged in to the application$")
	public void iShouldBeLoggedInToTheApplication() throws Throwable {
		Assert.assertTrue(viewEmailId.waitFor(10).isDisplayed(), "User is not able to sign-in");
		Assert.assertEquals(viewEmailId.getText(), Properties.getVariable("signInEmail"), "User email doesn't match");
	}
}
