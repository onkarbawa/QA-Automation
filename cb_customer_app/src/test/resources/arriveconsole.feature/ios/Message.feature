@messaging
Feature: iOS | ArriveConsole | Messaging

  @iOS @TCS04
  Scenario: Verify while tap on cancel Trip button Trip cancel pop-up message is displayed
    Given I launch ARRIVE Console application
    And I accept notifications alert
    And I select site which have open trips
    And I tap on open trip
    When I tap on cancel trip button
    Then I saw cancel alert message
