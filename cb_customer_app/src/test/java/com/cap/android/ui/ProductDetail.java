package com.cap.android.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 13/09/17.
 */
public class ProductDetail extends AbstractScreenCap {

    UIElement productImage = UIElement.byId("com.curbside.nCap:id/imgSelectedProduct");
    UIElement lblProductName = UIElement.byId("com.curbside.nCap:id/tvProductName");
    UIElement linkRetailerWebsite = UIElement.byId("com.curbside.nCap:id/tvRetailerWebsite");
    UIElement lblSKU = UIElement.byId("com.curbside.nCap:id/tvSKUS");
    UIElement lblDescription = UIElement.byId("com.curbside.nCap:id/tvDescriptionHeader");
    UIElement lblOverview = UIElement.byId("com.curbside.nCap:id/tvOverviewHeader");
    UIElement btnBack = UIElement.byId("com.curbside.nCap:id/imgBack");

    @And("^I should see product details on the screen$")
    public void iShouldSeeProductDetail() throws Throwable {
        MobileDevice.getScreenshot(true);
        Assert.assertTrue(productImage.waitFor(2).isDisplayed(), "Product Image is not displayed");
        Assert.assertTrue(lblProductName.isDisplayed(), "Product Name is not displayed");
        Assert.assertTrue(linkRetailerWebsite.isDisplayed(), "Product Link to the Retailer website is not displayed");
        Assert.assertTrue(lblSKU.isDisplayed(), "Product SKU is not displayed");
        Assert.assertTrue(lblDescription.isDisplayed(), "Product Description is not displayed");
        lblOverview.swipeUpSlow();
        Assert.assertTrue(lblOverview.isDisplayed(), "Product Description is not displayed");
        btnBack.tap();
    }
}
