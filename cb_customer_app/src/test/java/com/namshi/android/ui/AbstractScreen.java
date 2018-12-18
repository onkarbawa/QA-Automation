package com.namshi.android.ui;

import com.cap.ios.ui.*;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;

public class AbstractScreen {



    static CountryScreen countryScreen;

    static UIElement loadingIcon = UIElement.byAccessibilityId("In progress");

    static {
        countryScreen = new CountryScreen();
    }
}
