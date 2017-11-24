package com.curbside.ios.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.PlivoUtil;
import com.curbside.automation.uifactory.MobileDevice;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by bawa.onkar on 8/9/17.
 */
public class SMSNotification extends AbstractScreen {

    SoftAssert softAssert = new SoftAssert();

    @And("^I check there is no latest SMS from Curbside$")
    public void iCheckThereIsNoLatestSMSFromCurbisde() throws Throwable {
        String startDateAndTime = PlivoUtil.getDateAndTime();
        Properties.setVariable("startTime", startDateAndTime);
        Reporter.addStepLog("Will search for message after " + startDateAndTime + " time stamp");
    }

    @Then("^I (.*) receive (?:welcome|order) SMS from Curbside$")
    public void iCheckLatestSMS(String condition) throws Throwable {
        boolean msgReceived;
        String startDateAndTime = Properties.getVariable("startTime");

        for (int i = 0; i < 2; i++) {
            Thread.sleep(40000);
            MobileDevice.getSource();
            Thread.sleep(40000);
            MobileDevice.getSource();
        }
        MobileDevice.getScreenshot(true);
        msgReceived = PlivoUtil.iSearchForSMS("12815020029", startDateAndTime);

        if (condition.equalsIgnoreCase("will"))
            softAssert.assertTrue(msgReceived, "Checked for SMS not able to receive the SMS yet");
        else if (condition.equalsIgnoreCase("should"))
            Assert.assertTrue(msgReceived, "Checked for SMS not able to receive the SMS yet");
        else Assert.fail("Please enter correct condition for assertion");
    }
}
