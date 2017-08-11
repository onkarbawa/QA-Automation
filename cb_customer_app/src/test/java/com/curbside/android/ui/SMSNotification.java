package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.PlivoUtil;
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

    @And("^I check there is no latest SMS from Curbisde$")
    public void iCheckThereIsNoLatestSMSFromCurbisde() throws Throwable {

        Properties.setVariable("msgCount",
                String.valueOf(PlivoUtil.getInboundMsgCount("MAMZQ1YWQWZDGYY2E5YT",
                        "YjQ3NjY5ZWFjZWJiM2EwNzBmYjQzNzE2YTNlM2Q3")));

    }

    @Then("^I should receive welcome SMS from Curbside app$")
    public void iCheckLatestSMS() throws Throwable {
        int previousMsgCount = Integer.parseInt(Properties.getVariable("msgCount"));
        Assert.assertTrue(PlivoUtil.isSmsReceived("MAMZQ1YWQWZDGYY2E5YT",
                "YjQ3NjY5ZWFjZWJiM2EwNzBmYjQzNzE2YTNlM2Q3","12815020030", previousMsgCount),
                "User has not received the SMS yet");

    }
}
