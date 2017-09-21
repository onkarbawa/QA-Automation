package com.cap.android.ui;

import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by hitesh.grover on 21/09/17.
 */
public class Pay {

    UIElement btnShowBarCodes = UIElement.byId("com.curbside.nCap:id/bShowBarCodes");
    UIElement btnReceiptTotal = UIElement.byId("com.curbside.nCap:id/bTotal");
    UIElement btnTakePicture = UIElement.byId("com.curbside.nCap:id/bTakePicture");
    UIElement btnShowBarCodesDone = UIElement.byId("com.curbside.nCap:id/tvCancelScan");
    UIElement btnReceiptTotalSave = UIElement.byId("com.curbside.nCap:id/tvSaveTotal");
    UIElement btnCameraCancel = UIElement.byId("com.android.camera2:id/closeButton");


    @And("^I scan the barcode that is visible on my screen$")
    public void iTapShowBarCodes() throws Throwable {
        btnShowBarCodes.waitFor(1).tap();
        btnShowBarCodesDone.tap();
    }

    @And("^I pay and enter total price$")
    public void iEnterReceiptTotal() throws Throwable {
        btnReceiptTotal.swipeUpSlow();
        btnReceiptTotal.tap();
        btnReceiptTotalSave.tap();
    }

    @And("^I take the picture of receipt$")
    public void iTakePicture() throws Throwable {
        btnTakePicture.swipeUpSlow();
        btnTakePicture.tap();
        Thread.sleep(2000);
        AndroidDevice.goBack();
        MobileDevice.getScreenshot(true);
    }
}
