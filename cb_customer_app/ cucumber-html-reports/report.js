$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/com.curbside.automation.customerApp/android/AppLaunch.feature");
formatter.feature({
  "line": 2,
  "name": "Curbside tutorial is prompted when app is launched first time and verify tutorial flow",
  "description": "",
  "id": "curbside-tutorial-is-prompted-when-app-is-launched-first-time-and-verify-tutorial-flow",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@applicationLaunch"
    }
  ]
});
formatter.scenario({
  "line": 5,
  "name": "Tapping \u0027Get Started\u0027 button should take you to Store Selection Page",
  "description": "",
  "id": "curbside-tutorial-is-prompted-when-app-is-launched-first-time-and-verify-tutorial-flow;tapping-\u0027get-started\u0027-button-should-take-you-to-store-selection-page",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@C114937"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "I launch the Android Customer App",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I scroll left 2 times",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I click on \u0027Get Started\u0027 button on the Intro page",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I click on \u0027Ok with me\u0027 button on access page",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I click on \u0027Allow Access Location\u0027 pop up",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "I should see the \u0027Account tab\u0027 button",
  "keyword": "Then "
});
formatter.step({
  "line": 12,
  "name": "I close the Curbside app",
  "keyword": "Then "
});
formatter.match({
  "location": "AppLaunch.iLaunchTheAndroidCustomerApp()"
});
formatter.result({
  "duration": 22762263373,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iClickThreeTimesForScrollToLeft()"
});
formatter.result({
  "duration": 10415720464,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iClickOnGetStartedButtonOnTheIntroPage()"
});
formatter.result({
  "duration": 5527298140,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iClickOnOkWithMeButtonOnAccessPage()"
});
formatter.result({
  "duration": 3514028243,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iClickOnAllowAccessLocationPopUp()"
});
formatter.result({
  "duration": 17499731326,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iShouldSeeTheCurrentLocationButton()"
});
formatter.result({
  "duration": 1039785678750,
  "error_message": "java.lang.AssertionError: Android : HomePage is not visible yet-- expected [true] but found [false]\n\tat org.testng.Assert.fail(Assert.java:94)\n\tat org.testng.Assert.failNotEquals(Assert.java:513)\n\tat org.testng.Assert.assertTrue(Assert.java:42)\n\tat com.curbside.automation.steps.android.AppLaunch.iShouldSeeTheCurrentLocationButton(AppLaunch.java:59)\n\tat ✽.Then I should see the \u0027Account tab\u0027 button(src/test/resources/com.curbside.automation.customerApp/android/AppLaunch.feature:11)\n",
  "status": "failed"
});
formatter.match({
  "location": "Common.iReleaseTheDriverSession()"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "line": 15,
  "name": "Tapping \u0027Skip Intro\u0027 button should take you to Store Selection Page",
  "description": "",
  "id": "curbside-tutorial-is-prompted-when-app-is-launched-first-time-and-verify-tutorial-flow;tapping-\u0027skip-intro\u0027-button-should-take-you-to-store-selection-page",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 14,
      "name": "@C114936"
    }
  ]
});
formatter.step({
  "line": 16,
  "name": "I launch the Android Customer App",
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "I click on \u0027Skip Intro\u0027 button on the screen",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "I click on \u0027Ok with me\u0027 button on access page",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "I click on \u0027Allow Access Location\u0027 pop up",
  "keyword": "When "
});
formatter.step({
  "line": 20,
  "name": "I should see the \u0027Account tab\u0027 button",
  "keyword": "Then "
});
formatter.step({
  "line": 21,
  "name": "I close the Curbside app",
  "keyword": "Then "
});
formatter.match({
  "location": "AppLaunch.iLaunchTheAndroidCustomerApp()"
});
formatter.result({
  "duration": 4274187,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iClickOnSkipIntroButtonOnTheScreen()"
});
formatter.result({
  "duration": 13739145951,
  "error_message": "org.openqa.selenium.NoSuchElementException: An element could not be located on the page using the given search parameters. (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 13.64 seconds\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.4.0\u0027, revision: \u0027unknown\u0027, time: \u0027unknown\u0027\nSystem info: host: \u0027Anils-MacBook-Air.local\u0027, ip: \u0027fe80:0:0:0:1c24:f1e6:1d0a:a94a%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.12.5\u0027, java.version: \u00271.8.0_131\u0027\nDriver info: io.appium.java_client.android.AndroidDriver\nCapabilities [{app\u003d/Users/kumar.anil/workspace-tft/QA-Automation/cb_customer_app/../Curbside.apk, appPackage\u003dcom.curbside.nCurbside, deviceScreenSize\u003d1080x1920, networkConnectionEnabled\u003dtrue, warnings\u003d{}, databaseEnabled\u003dfalse, deviceName\u003demulator-5554, platform\u003dLINUX, deviceUDID\u003demulator-5554, appActivity\u003dcom.curbside.nCurbside.app.help.SplashScreenActivity, desired\u003d{app\u003d/Users/kumar.anil/workspace-tft/QA-Automation/cb_customer_app/../Curbside.apk, appPackage\u003dcom.curbside.nCurbside, appActivity\u003dcom.curbside.nCurbside.app.help.SplashScreenActivity, platformName\u003dAndroid, deviceName\u003demulator-5554}, platformVersion\u003d8.0.0, webStorageEnabled\u003dfalse, locationContextEnabled\u003dfalse, takesScreenshot\u003dtrue, javascriptEnabled\u003dtrue, deviceModel\u003dAndroid SDK built for x86, platformName\u003dAndroid, deviceManufacturer\u003dGoogle}]\nSession ID: e777c399-e70e-48de-85b9-2ddc4079442e\n*** Element info: {Using\u003did, value\u003dcom.curbside.nCurbside:id/button_got_it}\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:215)\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:167)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:671)\n\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:42)\n\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n\tat io.appium.java_client.android.AndroidDriver.execute(AndroidDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:410)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:62)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.android.AndroidDriver.findElement(AndroidDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementById(RemoteWebDriver.java:453)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElementById(DefaultGenericMobileDriver.java:70)\n\tat io.appium.java_client.AppiumDriver.findElementById(AppiumDriver.java:1)\n\tat io.appium.java_client.android.AndroidDriver.findElementById(AndroidDriver.java:1)\n\tat org.openqa.selenium.By$ById.findElement(By.java:218)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:402)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.android.AndroidDriver.findElement(AndroidDriver.java:1)\n\tat com.curbside.automation.customerApp.android.pages.applicationLaunch.ApplicationLaunchPageAndroid.getSkipIntro(ApplicationLaunchPageAndroid.java:39)\n\tat com.curbside.automation.steps.android.AppLaunch.iClickOnSkipIntroButtonOnTheScreen(AppLaunch.java:65)\n\tat ✽.And I click on \u0027Skip Intro\u0027 button on the screen(src/test/resources/com.curbside.automation.customerApp/android/AppLaunch.feature:17)\n",
  "status": "failed"
});
formatter.match({
  "location": "AppLaunch.iClickOnOkWithMeButtonOnAccessPage()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AppLaunch.iClickOnAllowAccessLocationPopUp()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AppLaunch.iShouldSeeTheCurrentLocationButton()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "Common.iReleaseTheDriverSession()"
});
formatter.result({
  "status": "skipped"
});
});