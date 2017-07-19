package com.curbside.android.ui;

import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import io.appium.java_client.AppiumDriver;

/**
 * @author hitesh.grover
 * This is page that appears when you launch application for first time
 */
public class Welcome extends AbstractScreen {

  UIElement pageIndicator = UIElement.byId("com.curbside.nCurbside:id/page_indicator");
  UIElement skipIntro = UIElement.byUISelector("new UiSelector().text(\"Skip Intro\")");
  UIElement btnGetStarted = UIElement.byUISelector("new UiSelector().text(\"Get Started\")");
  UIElement btnAllow = UIElement.byUISelector("new UiSelector().text(\"Allow\")");
  UIElement okButton = UIElement.byUISelector("new UiSelector().text(\"OK\")");
  
  @And("^I wait for application to be launched$")
  public void wait_for_app_launch() throws Throwable {
	for (int i = 0; i < 10; i++) {
    	if(skipIntro.isDisplayed()) return;
    	if(btnGetStarted.isDisplayed()) return;
    	if(footerTabsScreen.btnMyAccount.isDisplayed()) return;
    	if(okButton.isDisplayed()) return;
    	if(btnAllow.isDisplayed()) return;
	}
    MobileDevice.getScreenshot(true);
  }
  
  @And("^I swipe left (\\d+) times on intro page$")
  public void iSwipeLeftTimesOnIntroPage(int count) throws Throwable {
    commonSteps.swipeLeft(count);
  }

  @And("^I tap on '(.*)' button on intro page$")
  public void iTapOnSkipIntroButtonOnIntroPage(String name) throws Throwable {
    pageIndicator.waitFor(5);
    Steps.tapButton(name);
  }
  
  public void skipInto() throws Throwable
  {
	  skipIntro.tap();
  }
}