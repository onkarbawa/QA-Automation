package com.curbside.android.ui;

/**
 * Created by kumar.nipun on 7/10/2017.
 */
public class AbstractScreen {
  static Welcome welcomeScreen;
  static Login loginScreen;


  static {
    welcomeScreen = new Welcome();
    loginScreen = new Login();
  }
}
