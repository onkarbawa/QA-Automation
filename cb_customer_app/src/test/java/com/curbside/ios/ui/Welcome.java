package com.curbside.ios.ui;

import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;

/**
 * @author kumar.anil
 * This is page that appears when you launch application for first time
 */
public class Welcome {

    UIElement skipIntro = new UIElement(By.name("Skip Intro"));
    UIElement okWithMe = new UIElement(By.name("OK with me"));
}