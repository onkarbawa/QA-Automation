package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.PlivoUtil;
import cucumber.api.java.en.And;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bawa.onkar on 8/9/17.
 */
public class SMSNotification extends AbstractScreen {

    @Test
    public void test(){
        String timeStamp = PlivoUtil.getInboundMessageTimeStamp("12815020029");
//        Date dNow = new Date(timeStamp);
//        SimpleDateFormat ft =
//                new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//
//        System.out.println("Current Date: " + ft.format(dNow));


        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        //Date startDate;

//            startDate = df.parse(timeStamp);
//            String newDateString = df.format(startDate);
//            System.out.println(newDateString);
      //  DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        // Convert from String to Date
        try {
            Date startDate = df.parse(timeStamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //    System.out.println(timeStamp);
    }
    public int currentMessageCount() throws Throwable {
        int count = PlivoUtil.getInboundMsgCount("12815020029");
        System.out.println(count+"Current count");
        return count;
    }

    @And("^I should see sms notification has been received$")
    public void iShouldSeeSmsNotificationHasBeenReceived() throws Throwable {
        Thread.sleep(1000);
        String previousMessageCount = Properties.getVariable("previousMessageCount");
        int previousMsgCount = Integer.parseInt(previousMessageCount);
        System.out.println(previousMsgCount +"previous count");
        Assert.assertNotEquals(previousMsgCount,currentMessageCount(),"Place-Order message is not received");

    }

    @And("^I get the previous message count$")
    public void iGetPreviousMessageCount() throws Throwable {
        int count = PlivoUtil.getInboundMsgCount("12815020029");
        Properties.setVariable("previousMessageCount",String.valueOf(count));
    }
}
