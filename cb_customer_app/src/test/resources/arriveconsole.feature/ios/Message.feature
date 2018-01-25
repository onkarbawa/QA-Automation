@messaging
Feature: iOS | ArriveConsole | Messaging

  @iOS
  Scenario: Setting up the environment | Arrive Console
    Given I launch ARRIVE Console application
    And I am on arriveConsole home screen
    And I confirm that current site is selected
    And I tap on 'View Trips' button
    When I add open trips for arriveConsole app
    Then I saw site header name and current open trips without map

  @iOS @TCS04
  Scenario: Verify details are shown when open a trip
    Given I tap on open trip
    Then I saw open trip details

  @iOS @TCS08
  Scenario: Verify while tap on cancel Trip button Trip cancel pop-up message is displayed
    Given I tap on open trip
    And I tap on cancel trip button
    And I saw cancel alert message
    When I tap on 'Yes, Cancel Trip' button
    Then I saw cancelled trip removed from the list

  @iOS @TCS09
  Scenario: Verify while tap on complete Trip button Trip complete pop-up message is displayed
    Given I tap on open trip
    And I tap on complete trip button
    And I saw complete alert message
    When I tap on 'Yes, Complete Trip' button
    Then I saw cancelled trip removed from the list