package com.curbside.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;

/**
 * @author hitesh.grover
 * My Account- After clicking My Account tab on footer strip
 */

public class MyAccount {

    static UIElement signUp= new UIElement(By.id("com.curbside.nCurbside:id/button_sign_up"));
    static UIElement email= new UIElement(By.id("com.curbside.nCurbside:id/text_email"));
    static UIElement paymentInfo= new UIElement(By.xpath("//android.widget.ListView/android.widget.RelativeLayout[@index='3']"));
    //static UIElement paymentInfoID= new UIElement(By.id("com.curbside.nCurbside:id/textview_my_account_listitem"));

    @And("^I tap on PaymentInfo button on Account page$")
    public void iTapOnPaymentInfoButtonOnAccountPage() throws Throwable {
        try{
            paymentInfo.tap();
            Thread.sleep(1000);
            paymentInfo.tap();
        }catch (Exception e){}

    }





}
