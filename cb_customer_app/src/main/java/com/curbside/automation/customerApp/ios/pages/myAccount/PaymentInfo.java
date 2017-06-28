package com.curbside.automation.customerApp.ios.pages.myAccount;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by bawa.onkar on 27/06/17.
 */
public class PaymentInfo extends Page{

    @FindBy(xpath = "//XCUIElementTypeStaticText[@label='Payment Info']")
    public WebElement addCreditCard;
    public PaymentInfo(AppiumDriver driver) {
        super(driver);
    }


}
