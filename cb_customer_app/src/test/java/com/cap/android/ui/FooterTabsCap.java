package com.cap.android.ui;

import com.curbside.automation.uifactory.UIElement;

/**
 * Created by hitesh.grover on 05/09/17.
 */
public class FooterTabsCap {

    UIElement btnTasks = UIElement.byUISelector("new UiSelector().text(\"Tasks\")");
    UIElement btnPickups = UIElement.byUISelector("new UiSelector().text(\"Pickups\")");
    UIElement btnPickupsScan = UIElement.byUISelector("new UiSelector().text(\"Pickup Scan\")");
    UIElement btnMyAccount = UIElement.byUISelector("new UiSelector().text(\"My Account\")");
    UIElement btnMore = UIElement.byUISelector("new UiSelector().text(\"More\")");

}
