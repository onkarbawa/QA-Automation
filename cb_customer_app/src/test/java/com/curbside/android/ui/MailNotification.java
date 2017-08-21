package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.Mailinator;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 21/08/17.
 */
public class MailNotification {

    @Then("^I should receive '(.*)' Email from Curbside app$")
    public void iCheckLatestEmail(String emailType) throws Throwable {

        String emailID = Properties.getVariable("signupEmail");
        emailID = emailID.split("@")[0];

        switch (emailType.toLowerCase()) {
            case "welcome":
                Assert.assertEquals(Mailinator.isMailReceived(emailID, "Welcome to Curbside"), true,
                        "Signup mail not received yet");
                break;

            case "in-progress":
                Assert.assertEquals(Mailinator.isMailReceived(emailID, "We’re Prepping Your Curbside Pickup Order"),
                        true,"In Progress mail not received yet");
                break;

            default:
                Assert.fail("Please rectify the correct email type in the feature file ");
        }

    }

}
