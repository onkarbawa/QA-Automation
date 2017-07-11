package com.curbside.android.ui;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

/**
 * Created by hitesh.grover on 03/07/17.
 */
public class Common {
    @And("^I am a logged in User$")
    public void iAmALoggedInUser() {

    }

    @And("^I enter '<Email>' , '<PhoneNumber>' and '<Password>' for Signup$")
    public void iEnterEmailPhoneNumberAndPasswordForSignup(String Email , String PhoneNumber , String Password) throws Throwable {

    }
}
