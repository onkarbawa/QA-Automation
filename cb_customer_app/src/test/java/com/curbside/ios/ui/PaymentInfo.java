package com.curbside.ios.ui;

import com.curbside.automation.uifactory.MobileDevice;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;

/**
 * Created by bawa.onkar on 06/07/17.
 */
public class PaymentInfo {

    @And("^I tap on 'Add a new card'$")
    public void iTapOnAddANewCard() throws Throwable {
        MobileDevice.tap(145,325);
    }
}
