package com.cap.ios.ui;

import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import com.curbside.ios.ui.AccountInfo;

/**
 * Created by bawa.onkar
 */
public class AbstractScreen {

    static Steps commonSteps;
    static Login loginScreen;
    static Tasks tasksScreen;
    static MyAccount myAccountScreen;
    static FooterTabs footerTabsScreen;
    static ProductDetail productDetailScreen;

    static UIElement loadingIcon = UIElement.byAccessibilityId("In progress");

    static {
        loginScreen = new Login();
        tasksScreen = new Tasks();
        myAccountScreen = new MyAccount();
        footerTabsScreen = new FooterTabs();
        productDetailScreen = new ProductDetail();
        commonSteps = new Steps();
    }
}
