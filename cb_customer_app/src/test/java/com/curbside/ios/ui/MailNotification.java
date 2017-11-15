package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.Mailinator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 21/08/17.
 */
public class MailNotification {

    @Then("^I should receive '(.*)' Email from Curbside app$")
    public void iCheckLatestEmail(String emailType) throws Throwable {
        String emailID;

        Thread.sleep(20000);

        switch (emailType.toLowerCase()) {
            case "welcome":
                emailID = Properties.getVariable("signupEmail");
                emailID = emailID.split("@")[0];
                Assert.assertEquals(Mailinator.isMailReceived(emailID, "Welcome to curbside"), true,
                        "Signup mail not received yet");
                break;

            case "progress":
                emailID = Properties.getVariable("signupEmail");
                emailID = emailID.split("@")[0];
                Assert.assertEquals(Mailinator.isMailReceived(emailID, "We’re Prepping Your Curbside Pickup Order"),
                        true,"In Progress mail not received yet");
                break;

            case "in-progress":
                Assert.assertEquals(Mailinator.isMailReceived("live_order", "We’re Prepping Your Curbside Pickup Order"),
                        true,"In Progress mail not received yet");
                break;

            case "delivery-order":
                Assert.assertEquals(Mailinator.isMailReceived("live_order", "We’re Prepping Your Delivery Order"),
                        true,"Delivery Order mail not received yet");
                break;

            case "in-progress curbside pickup":
                Assert.assertEquals(Mailinator.isMailReceived("palo_alto", "We’re Prepping Your Curbside Pickup Order"),
                        true,"In Progress mail not received yet");
                break;

            case "in-progress delivery-order":
                Assert.assertEquals(Mailinator.isMailReceived("palo_alto", "We’re Prepping Your Delivery Order"),
                        true,"Delivery Order mail not received yet");
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
