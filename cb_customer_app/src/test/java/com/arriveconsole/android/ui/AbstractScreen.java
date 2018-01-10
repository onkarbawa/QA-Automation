package com.arriveconsole.android.ui;

import com.curbside.automation.uifactory.Steps;

/**
 * Created by hitesh.grover on 08/01/18.
 */
public class AbstractScreen {

    static Welcome welcomeScreen;
    static Steps commonSteps;
    static Trips tripsScreen;
    static ArriveTester arriveTester;
    static SiteSelection siteSelectionScreen;

    static {
        welcomeScreen = new Welcome();
        commonSteps = new Steps();
        tripsScreen = new Trips();
        arriveTester = new ArriveTester();
        siteSelectionScreen = new SiteSelection();
    }
}
