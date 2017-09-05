package com.cap.android.ui;

import com.curbside.android.ui.*;
import com.curbside.automation.uifactory.Steps;

/**
 * Created by hitesh.grover
 */
public class AbstractScreenCap {

  static Steps commonSteps;
  static FooterTabsCap footerTabsCap;

  static {
    commonSteps = new Steps();
    footerTabsCap = new FooterTabsCap();
  }
}
