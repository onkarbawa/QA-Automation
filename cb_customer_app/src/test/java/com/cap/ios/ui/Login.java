package com.cap.ios.ui;

import com.curbside.automation.uifactory.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboardWithKeyName;
import io.appium.java_client.MobileCommand;
import io.appium.java_client.remote.HideKeyboardStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

/**
 * Created by bawa.onkar
 */

public class Login extends AbstractScreen{

    UIElement accountName = UIElement.byXpath("//XCUIElementTypeImage[@name='LogoIcon']/following-sibling::XCUIElementTypeOther[1]/XCUIElementTypeTextField");
    UIElement userName = UIElement.byXpath("//XCUIElementTypeImage[@name='LogoIcon']/following-sibling::XCUIElementTypeOther[2]/XCUIElementTypeTextField");
    UIElement password = UIElement.byXpath("//XCUIElementTypeImage[@name='LogoIcon']/following-sibling::XCUIElementTypeOther[3]/XCUIElementTypeSecureTextField");
    UIElement lblLoginErrorMsg = UIElement.byXpath("//XCUIElementTypeImage[@name='ErrorIcon']/following-sibling::XCUIElementTypeStaticText");
    UIElement stagingVariable = UIElement.byXpath("//XCUIElementTypeButton[@name='Login']/following-sibling::XCUIElementTypeButton");
    UIElement storeID = UIElement.byClass("XCUIElementTypeTextField");
    UIElement btnReturnKeyboard = UIElement.byXpath("//XCUIElementTypeKeyboard//XCUIElementTypeButton[@name='Return']");

    UIElement btnAllow = UIElement.byName("Allow");
    UIElement btnOK = UIElement.byName("OK");

    UIElement btnStaging = UIElement.byName("Staging");

    @And("^I enter \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" for login$")
    public void iEnterAnd(String account, String user, String pass) throws Throwable {
        accountName.clearText();
        accountName.sendKeys(account, false);
        userName.clearText();
        userName.sendKeys(user, false);
        password.sendKeys(pass, false);
    }

    @Given("^I accept CAP notifications alerts$")
    public void acceptNotificationAlert() throws Throwable {
        for (int i = 0; i < 7; i++) {
            if (btnAllow.isDisplayed()||btnOK.isDisplayed()){
                try {
                    try {
                        new UIElement(By.name("Allow")).tap();
                    } catch (Exception e) {
                        new UIElement(By.name("OK")).tap();
                    }
                } catch (Exception e) {
                }
            }

        }

    }

    @And("^I should see this \"([^\"]*)\" on the '(.*)' screen$")
    public void iShouldSeeErrorMsgOn(String expectedErrorMsg,String screen) throws Throwable {
        String actualErrorMsg = null;
        if (screen.equalsIgnoreCase("Login")){
            actualErrorMsg = lblLoginErrorMsg.waitFor(2).getText();
        }else {
            actualErrorMsg =  tasksScreen.errorMessage.waitFor(3).getText();
            actualErrorMsg = actualErrorMsg.substring(3,43);
        }

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Got different error message");
    }

    @And("^I have selected test environment for cap$")
    public void iHaveSelectedTestEnvironmentForCap() throws Throwable {
        loginScreen.acceptNotificationAlert();

        String envAPIKey = "cvs_9945";
        if(footerTabsScreen.btnTask.isDisplayed() || stagingVariable.getText().contains(".s EE (cvs_9945)") ||
                stagingVariable.getText().contains(".s SE (cvs_9945)"))
            return;
//        if (DriverFactory.getEnvironment().equalsIgnoreCase(envAPIKey))
//            return;

        for (int i = 0;i<4;i++){
            stagingVariable.tapCenter();
        }
        stagingVariable.longPress(10);
        btnStaging.tap();
        storeID.clearText();
        storeID.sendKeys(envAPIKey,false);
      //  storeID.sendKeys(Keys.ENTER);
   //     MobileCommand.hideKeyboardCommand("Return");
        btnReturnKeyboard.tap();
        Steps.tapButton("OK");
        storeID.waitForNot(20);
        try {
            Steps.tapButton("OK");
        }catch (Exception e){}
        MobileDevice.getScreenshot(true);
        DriverFactory.setEnvironment(envAPIKey);
        DriverFactory.closeApp();
        DriverFactory.launchApp();

        loginScreen.acceptNotificationAlert();
    }
}


