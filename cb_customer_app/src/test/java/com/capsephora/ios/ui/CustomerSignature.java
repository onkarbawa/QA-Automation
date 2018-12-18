package com.capsephora.ios.ui;

import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

/**
 * Created by hitesh.grover
 */

public class CustomerSignature {
    UIElement signatureBlankSpace = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Customer Signature')]/" +
            "following-sibling::XCUIElementTypeOther");
    UIElement btnDoneSigning = UIElement.byName("Done Signing");

    @And("^I sign the customer's signature$")
    public void iDrawSignature() throws Throwable {
        int noOfDraws = 0;

        while (!btnDoneSigning.getElement().isEnabled() && noOfDraws < 5) {
            int startX = signatureBlankSpace.getX();
            int startY = signatureBlankSpace.getY();
            int width = signatureBlankSpace.getWidth();
            int height = signatureBlankSpace.getHeight();

            new TouchAction((PerformsTouchActions) DriverFactory.getDriver()).press(PointOption.point(startX + (width / 2), startY + (height / 2))).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(-width, startY + (height / 2))).release().perform();

            new TouchAction((PerformsTouchActions) DriverFactory.getDriver()).press(PointOption.point(startX + (width / 2), startY + (height / 2))).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(-width, 0)).release().perform();

            ++noOfDraws;
        }

        MobileDevice.getScreenshot(true);
    }
}
