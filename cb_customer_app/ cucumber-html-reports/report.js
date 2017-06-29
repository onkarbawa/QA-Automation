$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/com.curbside.automation.customerApp/ios/AppLaunch.feature");
formatter.feature({
  "line": 2,
  "name": "Check information\u0027s and functionality\u0027s on Applaunch in Customer app",
  "description": "",
  "id": "check-information\u0027s-and-functionality\u0027s-on-applaunch-in-customer-app",
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
  "name": "Verify application is able to launch and checkmark is set to \u0027Always\u0027",
  "description": "",
  "id": "check-information\u0027s-and-functionality\u0027s-on-applaunch-in-customer-app;verify-application-is-able-to-launch-and-checkmark-is-set-to-\u0027always\u0027",
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
  "name": "I launch the Customer App",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I clicked on Allow to send notifications",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I click \"2\" times for Scroll left",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I click on \u0027Get Started\u0027 button",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I click on \u0027Ok with me\u0027 button on access landing page",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "I click on \u0027Allow Access Location\u0027 button",
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "I should see the \u0027Store Selection Page\u0027",
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "I click on Home button",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "I click on \u0027Settings\u0027 application",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "I scroll down and click on Curbside App",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "I click on \u0027Location\u0027",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "I saw checkmark is set \u0027Always\u0027",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "I close the web driver session for ios",
  "keyword": "And "
});
formatter.match({
  "location": "AppLaunchSteps.iLaunchTheCustomerApp()"
});
formatter.result({
  "duration": 35102220992,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunchSteps.iClickedOnAllowToSendNotifications()"
});
formatter.result({
  "duration": 2868399109,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 9
    }
  ],
  "location": "AppLaunchSteps.iClickTimesForScrollLeft(int)"
});
formatter.result({
  "duration": 11610190073,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunchSteps.iClickOnGetStartedButton()"
});
formatter.result({
  "duration": 2126077490,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunchSteps.iClickOnOkWithMeButtonOnAccessLandingPage()"
});
formatter.result({
  "duration": 2702502942,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunchSteps.iClickOnAllowAccessLocationButton()"
});
formatter.result({
  "duration": 1900334449,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunchSteps.iShouldSeeTheStoreSelectionPage()"
});
formatter.result({
  "duration": 5150945812,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunchSteps.iClickOnHomeButton()"
});
formatter.result({
  "duration": 35062,
  "status": "passed"
});
formatter.match({
  "location": "AppLaunchSteps.iClickOnSettingsApplication()"
});
