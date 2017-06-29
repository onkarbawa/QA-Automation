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
  "duration": 32437598676,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iClickThreeTimesForScrollToLeft()"
});
formatter.result({
  "duration": 6319657505,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iClickOnGetStartedButtonOnTheIntroPage()"
});
formatter.result({
  "duration": 1562024167,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iClickOnOkWithMeButtonOnAccessPage()"
});
formatter.result({
  "duration": 1687965062,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iClickOnAllowAccessLocationPopUp()"
});
formatter.result({
  "duration": 11663465764,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iShouldSeeTheCurrentLocationButton()"
});
formatter.result({
  "duration": 2784545758,
  "status": "passed"
});
formatter.match({
  "location": "Common.iReleaseTheDriverSession()"
});
formatter.result({
  "duration": 5296589549,
  "status": "passed"
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
  "duration": 26010474855,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iClickOnSkipIntroButtonOnTheScreen()"
});
formatter.result({
  "duration": 3235380218,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iClickOnOkWithMeButtonOnAccessPage()"
});
formatter.result({
  "duration": 1755928872,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iClickOnAllowAccessLocationPopUp()"
});
formatter.result({
  "duration": 11565732123,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunch.iShouldSeeTheCurrentLocationButton()"
});
formatter.result({
  "duration": 215666870,
  "status": "passed"
});
formatter.match({
  "location": "Common.iReleaseTheDriverSession()"
});
formatter.result({
  "duration": 5039407925,
  "status": "passed"
});
});