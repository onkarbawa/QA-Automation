package com.curbside.android.ui;

import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
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
    UIElement leadTime = UIElement.byId("com.curbside.nCurbside:id/text_status_view");
    UIElement btnSearch = UIElement.byId("com.curbside.nCurbside:id/edit_search_view");
    UIElement secondProduct = UIElement.byXpath("//*[@resource-id='com.curbside.nCurbside:id/itemCard' and @index='2']");



    @Given("I select 1st product from list")
    public void select1stProduct() throws Throwable {
        firstProduct.waitFor(15).tap();
    }

    @And("I select 2nd product from list")
    public void select2ndProduct() throws Throwable {
        secondProduct.waitFor(15).tap();
    }

    @Then("^I should see the lead time below the store address on Store detail page$")
    public void iShouldSeeTheLeadTimeBelowTheStoreAddressOnStoreDetailPage() throws Throwable {
        Assert.assertTrue(leadTime.waitFor(5).isDisplayed(), "Lead time is not displayed");
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

    @And("^I search for '(.*)' product from '(.*)' Screen$")
    public void iSearchForProductFromStoreOrHomeScreen(String product , String screenName) throws Throwable {
        if(screenName.equalsIgnoreCase("Store")){
            footerTabsScreen.tapShop();
            homeScreen.iSelectWestFieldStore();
            btnSearch.waitFor(5).tap();
        }else if(screenName.equalsIgnoreCase("Home")){
            footerTabsScreen.tapMyAccount();
            homeScreen.searchIcon.tap();
        }
        homeScreen.searchBox.sendKeys(product, false);
        AndroidDevice.pressEnter();
    }

    @And("^I add any product to the cart from store$")
    public void iAddAnyProductToTheCartFromStore() throws Throwable {
        footerTabsScreen.tapShop();
        homeScreen.select1stRetailerPartner();
        select1stProduct();
        productDetailsScreen.addToCart();
        AndroidDevice.goBack();
    }
}
