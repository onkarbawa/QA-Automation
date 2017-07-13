package com.curbside.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 12/07/17.
 */
public class StoreDetails extends AbstractScreen {

    UIElement firstProduct = UIElement.byXpath("//org.lucasr.twowayview.TwoWayView/" +
            "android.widget.FrameLayout[@index='0']");
    UIElement productImage = UIElement.byXpath("//org.lucasr.twowayview.TwoWayView/" +
            "android.widget.FrameLayout[@index='0']//*[@resource-id='com.curbside.nCurbside:id/image_product_icon']");
    UIElement productName = UIElement.byXpath("//org.lucasr.twowayview.TwoWayView/" +
            "android.widget.FrameLayout[@index='0']//*[@resource-id='com.curbside.nCurbside:id/text_product_name']");



    @Given("I select 1st product from list")
    public void select1stProduct() throws Throwable {
        firstProduct.waitFor(10).tap();
    }

    @Then("^I should see following products listed on partner screen$")
    public void iShouldSeeFollowingProductsListedOnPartnerScreen() throws Throwable {
        Assert.assertTrue(firstProduct.waitFor(10).isDisplayed(), "There no product in the store");
        Assert.assertTrue(productImage.isDisplayed(),"Product Image is not displayed");
        Assert.assertTrue(productName.isDisplayed(),"Product Name is not displayed");
    }

    @When("^I tap on product from the list$")
    public void iTapOnProductFromTheList() throws Throwable {
        select1stProduct();
    }
}
