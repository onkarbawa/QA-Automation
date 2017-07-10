package com.curbside.android.ui;

import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * @author hitesh.grover
 * This is page that appears when you launch application for first time
 */
public class Welcome extends AbstractScreen {

  UIElement pageIndicator = UIElement.byId("com.curbside.nCurbside:id/page_indicator");
  UIElement skipIntro = UIElement.byXpath("//*[@text='Skip Intro']");
  UIElement okButton = UIElement.byXpath("//*[@text='OK']");

  @And("^I swipe left (\\d+) times on intro page$")
  public void iSwipeLeftTimesOnIntroPage(int count) throws Throwable {
    pageIndicator.waitFor(5);
    new Steps().swipeLeft(count);
  }

  @And("^I tap on '(.*)' button on intro page$")
  public void iTapOnSkipIntroButtonOnIntroPage(String name) throws Throwable {
    pageIndicator.waitFor(5);
    Steps.tapButton(name);
  }
}