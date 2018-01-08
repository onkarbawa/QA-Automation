@messaging
Feature: iOS | ArriveConsole | Messaging

  @iOS @TCS08
  Scenario: Verify while tap on cancel Trip button Trip cancel pop-up message is displayed
    Given I launch ARRIVE Console application
    And I accept notifications alert
    And I select site which have open trips
    And I tap on open trip
    When I tap on cancel trip button
    Then I saw cancel alert message
    When I tap on 'Yes, Cancel Trip' button
    Then I saw cancelled trip removed from the list
    And I am on arriveConsole home screen

  

