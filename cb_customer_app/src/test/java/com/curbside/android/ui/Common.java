package com.curbside.android.ui;


import cucumber.api.java.en.And;


/**
 * Created by hitesh.grover on 03/07/17.
 */
public class Common extends AbstractScreen {
    @And("^I am a logged in User$")
    public void iAmALoggedInUser() {

    }

    @And("^I enter '<Email>' , '<PhoneNumber>' and '<Password>' for Signup$")
    public void iEnterEmailPhoneNumberAndPasswordForSignup(String Email , String PhoneNumber , String Password) throws Throwable {

    }

    @And("^I tap on shop , accunt button$")
    public void iTapOnShopAccuntButton() throws Throwable {
        Thread.sleep(2000);
       footerTabsScreen.tapMyAccount();
       Thread.sleep(1000);
       footerTabsScreen.tapCart();
       Thread.sleep(1000);
       footerTabsScreen.tapShop();
        Thread.sleep(1000);
       footerTabsScreen.tapMyAccount();
    }
}
