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

        final Calendar cal = Calendar.getInstance();
        final Date currentTime = cal.getTime();
//        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        GMTDate = sdf.format(currentTime);

        Properties.setVariable("GMTDate", GMTDate);
        Properties.setVariable("msgCount",
                String.valueOf(PlivoUtil.getInboundMsgCount("12815020030", GMTDate, false)));
    }

    @Then("^I should receive welcome SMS from Curbside app$")
    public void iCheckLatestSMS() throws Throwable {
        int expectedCount = Integer.parseInt(Properties.getVariable("msgCount")) + 1;
        int actualCount = PlivoUtil.getInboundMsgCount("12815020030", GMTDate, false);
        Assert.assertEquals(expectedCount, actualCount, "User has not received the SMS yet");
        System.out.println("expectedCount-"+expectedCount + "actualCount-"+actualCount);
    }
}
