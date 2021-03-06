package com.curbside.android.ui;

import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.Arrays;

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
    UIElement titleStoreLocationAfterSearch = UIElement.byId("com.curbside.nCurbside:id/text_title");



    @Given("I select 1st product from list")
    public void select1stProduct() throws Throwable {
        firstProduct.waitFor(10).tap();
    }

    @And("I select 2nd product from list")
    public void select2ndProduct() throws Throwable {
        secondProduct.waitFor(10).tap();
    }

    @And("I select (\\d+) product from list")
    public void selectNthProduct(int nthProduct) throws Throwable {
        UIElement.byXpath("//*[@resource-id='com.curbside.nCurbside:id/itemCard' and @index= \'"+nthProduct+"\']")
                .waitFor(8)
                .tap();
    }

    @Then("^I should see the lead time below the store address on Store detail page$")
    public void iShouldSeeTheLeadTimeBelowTheStoreAddressOnStoreDetailPage() throws Throwable {
        Assert.assertTrue(leadTime.waitFor(5).isDisplayed(), "Lead time is not displayed");
    }

    @Then("^I should see following products listed on partner screen$")
    public void iShouldSeeFollowingProductsListedOnPartnerScreen() throws Throwable {
        firstProduct.waitFor(15);
        Assert.assertTrue(productImage.isDisplayed(),"Product Image is not displayed");
    }

    @When("^I tap on product from the list$")
    public void iTapOnProductFromTheList() throws Throwable {
        try{
            firstProduct.tap();
        }catch (Exception e){
            UIElement.byXpath("//*[@resource-id='com.curbside.nCurbside:id/itemCard' and @index= \'"+1+"\']")
                    .tap();
        }
    }

    @And("^I (?:select|am at) '(.*)' store and search for '(.*)' product$")
    public void iSearchForProductFromStoreOrHomeScreen(String storeName, String productName) throws Throwable {

        int storeIndex = 0;
        switch (storeName.toLowerCase()) {
            case "cvs":
                storeIndex = 0;
                break;
            case "pizza hut":
                storeIndex = 1;
                break;
            case "westfield valley fair":
                storeIndex = 3;
                break;
            case "sephora":
                storeIndex = 4;
                break;
            default:
                Assert.fail(" This store is not added in the code");

        }
        footerTabsScreen.tapShop();
        footerTabsScreen.tapShop();
        UIElement.byXpath("//*[@resource-id='com.curbside.nCurbside:id/grid_view']" +
                "/android.widget.RelativeLayout[@index=\'" + storeIndex + "\']")
                .waitFor(3).tap();
        btnSearch.waitFor(2).tap();
        homeScreen.searchBox.sendKeys(productName, false);
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
