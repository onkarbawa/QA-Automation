package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.PlivoUtil;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 09/08/17.
 */
public class SMSNotification {


    @And("^I check there is no latest SMS from Curbisde$")
    public void iCheckThereIsNoLatestSMSFromCurbisde() throws Throwable {
        System.out.println("latestCountBefore--"+ PlivoUtil.getInboundMsgCount("12815020030"));
        Properties.setVariable("msgCount", String.valueOf(PlivoUtil.getInboundMsgCount("12815020030")));
    }

    @Then("^I should receive welcome SMS from Curbside app$")
    public void iCheckLatestSMS() throws Throwable {
        System.out.println("latestCountAfter--"+ PlivoUtil.getInboundMsgCount("12815020030"));
        int expectedCount = Integer.parseInt(Properties.getVariable("msgCount")) + 1;
        int actualCount = PlivoUtil.getInboundMsgCount("12815020030");
        Assert.assertEquals(expectedCount, actualCount, "User has not received the SMS yet");
        System.out.println("expectedCount-"+expectedCount + "actualCount-"+actualCount);
    }
}
