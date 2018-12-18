package com.namshi.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class CountryScreen extends AbstractScreen {

    UIElement txtSelectCountry = UIElement.byId("com.namshi.android:id/choose_country_text_view");
    UIElement txtNamshiLogo = UIElement.byId("com.namshi.android:id/logo_image_view");



    @And("^I saw country selection screen$")
    public void iSawCountrySelectionScreen() throws Throwable {
        Assert.assertEquals(txtSelectCountry.waitFor(20).getText(),"Select your country");
    }

    @Then("^I saw gender selection screen$")
    public void iSawGenderSelectionScreen() throws Throwable {
        Assert.assertTrue(txtNamshiLogo.waitFor(20).isDisplayed());
    }
}
