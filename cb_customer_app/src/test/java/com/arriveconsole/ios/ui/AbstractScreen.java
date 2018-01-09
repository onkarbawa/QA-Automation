package com.arriveconsole.ios.ui;

import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;

/**
 * Created by bawa.onkar.
 */
public class AbstractScreen {
    static Welcome welcomeScreen;
    static Steps commonSteps;
    static Trips tripsScreen;
    static SiteSelection siteSelection;
    static DebugMode debugModeScreen;
    static TripDetails tripDetailsScreen;
    static ArriveConsoleTester arriveConsoleTesterScreen;

    static UIElement loadingIcon = UIElement.byAccessibilityId("In progress");

    static {
        welcomeScreen = new Welcome();
        commonSteps = new Steps();
        tripsScreen = new Trips();
        siteSelection = new SiteSelection();
        debugModeScreen = new DebugMode();
        tripDetailsScreen = new TripDetails();
        arriveConsoleTesterScreen = new ArriveConsoleTester();
    }

    void waitForScreenToLoad() throws Throwable {
        loadingIcon.waitForNot(30);
    }
}
