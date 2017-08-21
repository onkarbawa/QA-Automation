package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.Mailinator;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 21/08/17.
 */
public class MailNotification {

    @Then("^I should receive welcome Email from Curbside app$")
    public void iCheckLatestEmail() throws Throwable {
        String emailID = Properties.getVariable("signupEmail");
        System.out.println("emailID--"+emailID);
        emailID = emailID.split("@")[0];
        Assert.assertEquals(Mailinator.isMailReceived(emailID), true, "Mail not received yet");
    }
}
