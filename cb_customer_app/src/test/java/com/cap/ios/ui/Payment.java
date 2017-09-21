package com.cap.ios.ui;

import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.SwipeDirection;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;

/**
 * Created by bawa.onkar
 */
public class Payment extends AbstractScreen {


    @And("^I '(.*)' screen$")
    public void iGoToPaymentScreen(String button) throws Throwable {
//        issueScreen.secondGotItBtn.tap();
//        commonSteps.iTapOnBackButton();
        Steps.tapButton(button);
    }

    @And("^I scan Barcodes and tap on '(.*)' button$")
    public void iScanBarcodesAndTapOn(String button) throws Throwable {
        Steps.tapButton(button);
        try {
            UIElement.byName("Next >").tap();
        }catch (Exception e){}
        Steps.tapButton("Done");
    }

    @And("^I tap on '(.*)' and enter receipt total price$")
    public void iTapOnAndEnterReceiptTotalPrice(String button) throws Throwable {
        Steps.tapButton(button);
        Steps.tapButton("Save");
    }

    @And("^I tap on '(.*)' button and '(.*)' to scan barcode$")
    public void iTapOnButtonAndUsePhotoToScanBarcode(String button1,String button2) throws Throwable {
        if (UIElement.byName(button1).isDisplayed()) {
            UIElement.byName(button1).tap();
        }else {
            UIElement.byName(button1).scrollTo().tap();
        }
       // Steps.tapButton(button1);
        try {
            UIElement.byName("OK").tap();
        }catch (Exception e){}
        Steps.tapButton("PhotoCapture");
        Steps.tapButton(button2);
        Thread.sleep(3000);
        Steps.tapButton("Cancel");
    }
}
