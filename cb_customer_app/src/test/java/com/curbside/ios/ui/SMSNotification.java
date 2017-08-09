package com.curbside.ios.ui;

import com.curbside.automation.common.utilities.PlivoUtil;
import cucumber.api.java.en.And;

import java.util.List;

/**
 * Created by bawa.onkar on 8/9/17.
 */
public class SMSNotification extends AbstractScreen {

    PlivoUtil plivoUtil = new PlivoUtil();




    @And("^I should see sms notification has been received$")
    public void iShouldSeeSmsNotificationHasBeenReceived() throws Throwable {
        // Argument1: authId and Argument2: authToken
        List<String> allSms = plivoUtil.getAllMessage("MAMZQ1YWQWZDGYY2E5YT", "YjQ3NjY5ZWFjZWJiM2EwNzBmYjQzNzE2YTNlM2Q3");
        for(String sms : allSms){
          //  System.out.println(sms);
        }
    }
}
