package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.Mailinator;
import com.curbside.automation.uifactory.MobileDevice;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 21/08/17.
 */
public class MailNotification {

    @Then("^I should receive '(.*)' email on '(.*)' EmailID$")
    public void iCheckLatestEmail(String emailType, String emailID) throws Throwable {

        if (emailID.equalsIgnoreCase("signup")) {
            emailID = Properties.getVariable("signupEmail");
            emailID = emailID.split("@")[0];
        }


        switch (emailType.toLowerCase()) {
            case "welcome":
                MobileDevice.getScreenshot(true);
                Assert.assertEquals(Mailinator.isMailReceived(emailID, "Welcome to Curbside"), true,
                        "Signup mail not received yet");
                break;

            case "in-progress pickup":
                MobileDevice.getScreenshot(true);
                Assert.assertEquals(Mailinator.isMailReceived(emailID, "We’re Prepping Your Curbside Pickup Order"),
                        true, "In Progress pickup mail not received yet");
                break;

            case "in-progress delivery":
                MobileDevice.getScreenshot(true);
                Assert.assertEquals(Mailinator.isMailReceived(emailID, "We’re Prepping Your Delivery Order"),
                        true, "In Progress delivery mail not received yet");
                break;

            default:
                Assert.fail("Please rectify the correct email type in the feature file ");
        }

    }

    @Given("^The mail box of userID \"([^\"]*)\" is empty$")
    public void theMailBoxOfUserIDIsEmpty(String userID) throws Throwable {
        Mailinator.deleteMails(userID);
    }
}
