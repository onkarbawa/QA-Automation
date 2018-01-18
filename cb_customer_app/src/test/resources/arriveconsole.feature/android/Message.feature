@messaging
Feature: Android | ArriveConsole | Messaging

  Scenario: Setting up the environment | Arrive Console
    Given I launch ARRIVE Console application
    And I am on arriveConsole home screen
    And I generate trips if not present

  @android @TCS08
  Scenario: Verify while tap on cancel Trip button Trip cancel pop-up message is displayed
    Given I launch ARRIVE Console application
    Then I saw site header name and current open trips without map
    And I tap on open trip
    When I tap on cancel trip button
    Then I saw cancel alert message
    When I tap on 'YES, CANCEL TRIP' button
    Then I saw trip removed from the list

  @android @TCS09
  Scenario: Verify while tap on complete Trip button Trip complete pop-up message is displayed
    Given I tap on open trip
    When I tap on complete trip button
    Then I saw complete alert message
    When I tap on 'YES, COMPLETE TRIP' button
    Then I saw trip removed from the list