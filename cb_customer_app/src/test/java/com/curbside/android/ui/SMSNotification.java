package com.curbside.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.PlivoUtil;
import com.curbside.automation.uifactory.MobileDevice;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by hitesh.grover on 09/08/17.
 */
public class SMSNotification {

    String GMTDate;

    @And("^I check there is no latest SMS from Curbside$")
    public void iCheckThereIsNoLatestSMSFromCurbside() throws Throwable {

        int previousMsgCount = PlivoUtil.getInboundMsgCount("MAMZQ1YWQWZDGYY2E5YT",
                "YjQ3NjY5ZWFjZWJiM2EwNzBmYjQzNzE2YTNlM2Q3");
        Reporter.addStepLog("Message count before SMS : " + previousMsgCount);
        Properties.setVariable("msgCount", String.valueOf(previousMsgCount));
    }

    @Then("^I should receive (?:welcome|order) SMS from Curbside app$")
    public void iCheckLatestSMS() throws Throwable {
        boolean msgReceived = false;
        boolean status;

        for (int i = 0; i < 2; i++) {
            Thread.sleep(40000);
            MobileDevice.getScreenshot(false);
            Thread.sleep(40000);
            //MobileDevice.getSource();
        }
        MobileDevice.getScreenshot(true);
        int previousMsgCount = Integer.parseInt(Properties.getVariable("msgCount"));
        for (int i = 0; i < 3; i++) {
            Reporter.addStepLog("-------Checking for SMS (" + (i + 1) + "/3) time-------");
            status = PlivoUtil.isSmsReceived("MAMZQ1YWQWZDGYY2E5YT",
                    "YjQ3NjY5ZWFjZWJiM2EwNzBmYjQzNzE2YTNlM2Q3", "12815020030", previousMsgCount);
            if (status) {
                msgReceived = true;
                break;
            }
        }
        Assert.assertTrue(msgReceived, "Checked for SMS 3 times but not able to receive the SMS yet");
    }
}
