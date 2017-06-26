package com.curbside.automation.steps;

import com.curbside.automation.uifactory.DriverFactory;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by bawa.onkar on 22/06/17.
 */
public class


DummySteps {
    
	@Given("^I launch application$")
	public void i_launch_application() throws Throwable {
	    DriverFactory.getDriver("iOS");
	}

	@When("^I tap on something$")
	public void i_tap_on_something() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I should see screen with title$")
	public void i_should_see_screen_with_title() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

}
