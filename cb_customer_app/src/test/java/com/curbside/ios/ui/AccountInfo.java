package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.UIElement;

import cucumber.api.java.en.Then;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 06/07/17.
 */
public class AccountInfo {

	static UIElement txtFirstName= UIElement.byXpath("//*[@Label='First Name']/following-sibling::*");
    static UIElement txtLastName= UIElement.byXpath("//*[@Label='Last Name']/following-sibling::*");
    static UIElement txtEmail= UIElement.byXpath("//*[@Label='Email']/following-sibling::*");
    static UIElement txtMobile= UIElement.byXpath("//*[@Label='Mobile']/following-sibling::*");
    static UIElement txtPassword= UIElement.byXpath("//*[@Label='Password']/following-sibling::*");
    
    static UIElement btnSignOut= UIElement.byAccessibilityId("Sign Out");
    static UIElement btnBack= UIElement.byAccessibilityId("Back");
    static UIElement btnAccount= UIElement.byAccessibilityId("Account");
    static UIElement btnEdit= UIElement.byAccessibilityId("Edit");
    
    @Then("^I should see my signup information under Account Info$")
    public static void verifySignUpInfo() throws Throwable
    {
    	FooterTabs.tapMyAccount();
    	MyAccount.btnAccountInfo.tap();
    	
    	Assert.assertEquals(txtEmail.getText(), Properties.getVariable("signupEmail"));
    	Assert.assertEquals(txtMobile.getText(), Properties.getVariable("signupPassword"));
    	Assert.assertEquals(txtPassword.getText(), StringUtils.repeat("∙", Properties.getVariable("signupPhoneNumber").length()));    	
    }
}
