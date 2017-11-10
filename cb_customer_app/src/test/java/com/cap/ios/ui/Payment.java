package com.cap.ios.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.SwipeDirection;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;

import java.text.DecimalFormat;

/**
 * Created by bawa.onkar
 */
public class Payment extends AbstractScreen {

 //   UIElement btnCamera = UIElement.byXpath("//XCUIElementTypeCell[2]/XCUIElementTypeTextField");
    UIElement btnCamera = UIElement.byPredicate("value == 'Camera Roll'");
    UIElement cameraBtn = UIElement.byXpath("//XCUIElementTypeButton[@name='Camera Roll'] | //XCUIElementTypeStaticText" +
            "[@name='5'] | //XCUIElementTypeOther[XCUIElementTypeStaticText[@name='My Albums']]/preceding-sibling::" +
            "XCUIElementTypeCell[1]/XCUIElementTypeTextField");
    UIElement firstPhoto = UIElement.byXpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell[1] | " +
            "//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther");


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
        MobileDevice.getScreenshot(true);
    }

    @And("^I tap on '(.*)' and enter receipt total price$")
    public void iTapOnAndEnterReceiptTotalPrice(String button) throws Throwable {
        Steps.tapButton(button);
        MobileDevice.getScreenshot(true);
        Steps.tapButton("Save");
    }

    @And("^I tap on '(.*)' button and '(.*)' to scan barcode$")
    public void iTapOnButtonAndUsePhotoToScanBarcode(String btnTakePhoto, String btnUsePhoto) throws Throwable {
        if (UIElement.byName(btnTakePhoto).isDisplayed()) {
            UIElement.byName(btnTakePhoto).tap();
        } else {
            UIElement.byName(btnTakePhoto).scrollTo().tap();
        }
        MobileDevice.getScreenshot(true);
        // Steps.tapButton(button1);
        try {
            UIElement.byName("OK").tap();
            MobileDevice.getScreenshot(true);
        } catch (Exception e) {
        }
        try {
            try {
                cameraBtn.tap();
            } catch (Exception e) {
                btnCamera.tap();
            }
            firstPhoto.tap();
            firstPhoto.waitForNot(10);
            // Thread.sleep(7000);
            commonSteps.iTapOnBackButton();
        } catch (Exception e) {
            Steps.tapButton("PhotoCapture");
            Steps.tapButton(btnUsePhoto);
            Thread.sleep(3000);
            Steps.tapButton("Cancel");
        }
        MobileDevice.getScreenshot(true);
    }


    @And("^I enter receipt stored price for product '(.*)'$")
    public void iEnterReceiptStoredPrice(String productPrice) throws Throwable {
        Double firstProduct = 00.00;
        Double secondProduct = 00.00;

        if (productPrice + String.valueOf(1) != null) {
            firstProduct = Double.valueOf(Properties.getVariable(productPrice + String.valueOf(1)));
            System.out.println(firstProduct);
        }
        if (productPrice + String.valueOf(2) != null) {
            secondProduct = Double.valueOf(Properties.getVariable(productPrice + String.valueOf(2)));
        }
        Double totalPrice = firstProduct + secondProduct;

        DecimalFormat df = new DecimalFormat("00.00");

        UIElement.byClass("XCUIElementTypeTextField").sendCharacterByCharacter(String.valueOf(df.format(totalPrice)),
                false);
        MobileDevice.tap(250,330);
        //Steps.tapButton("Save");
    }
}
