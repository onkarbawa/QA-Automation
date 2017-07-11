package com.curbside.android.ui;

import com.curbside.automation.uifactory.Steps;

/**
 * Created by kumar.nipun on 7/10/2017.
 */
public class AbstractScreen {
  static Welcome welcomeScreen;
  static Login loginScreen;
  static SignUp signUpScreen;
  static Steps commonSteps;


  static {
    welcomeScreen = new Welcome();
    loginScreen = new Login();
    signUpScreen = new SignUp();
    commonSteps = new Steps();
  }
}
