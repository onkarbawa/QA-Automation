package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.PlivoUtil;
import com.curbside.automation.uifactory.MobileDevice;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import org.testng.annotations.Test;

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


    String GMTDate;

//    @And("^I should see sms notification has been received$")
//    public void iShouldSeeSmsNotificationHasBeenReceived() throws Throwable {
//        Thread.sleep(10000);
////        String previousMessageCount = Properties.getVariable("previousMessageCount");
////        int previousMsgCount = Integer.parseInt(previousMessageCount);
////        System.out.println(previousMsgCount +"previous count");
////        Assert.assertNotEquals(previousMsgCount,currentMessageCount(),"Place-Order message is not received");
//
//
//        int expectedCount = Integer.parseInt(Properties.getVariable("previousMessageCount")) + 1;
//        int actualCount = PlivoUtil.getInboundMsgCount("12815020029", GMTDate, false);
//        Assert.assertEquals(expectedCount, actualCount, "User has not received the SMS yet");
//        System.out.println("expectedCount-"+expectedCount + "actualCount-"+actualCount);
//    }

//    @And("^I get the previous message count$")
//    public void iGetPreviousMessageCount() throws Throwable {
//
//        final Calendar cal = Calendar.getInstance();
//        final Date currentTime = cal.getTime();
////        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//        GMTDate = sdf.format(currentTime);
//
//        Properties.setVariable("GMTDate", GMTDate);
//        Properties.setVariable("previousMessageCount",
//                String.valueOf(PlivoUtil.getInboundMsgCount("12815020029", GMTDate, false)));
//    }

    @And("^I check there is no latest SMS from Curbisde$")
    public void iCheckThereIsNoLatestSMSFromCurbisde() throws Throwable {

        Properties.setVariable("msgCount",
                String.valueOf(PlivoUtil.getInboundMsgCount("MAMZQ1YWQWZDGYY2E5YT",
                        "YjQ3NjY5ZWFjZWJiM2EwNzBmYjQzNzE2YTNlM2Q3")));

    }
    @Then("^I should receive (?:welcome|order) SMS from Curbside$")
    public void iCheckLatestSMS() throws Throwable {
        int previousMsgCount = Integer.parseInt(Properties.getVariable("msgCount"));
        Assert.assertTrue(PlivoUtil.isSmsReceived("MAMZQ1YWQWZDGYY2E5YT",
                "YjQ3NjY5ZWFjZWJiM2EwNzBmYjQzNzE2YTNlM2Q3","12815020029", previousMsgCount),
                "User has not received the SMS yet");
        MobileDevice.getScreenshot(true);
    }
}
