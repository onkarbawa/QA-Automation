@tripView
Feature: iOS | ArriveConsole | Trip View

  @iOS @TCS04
  Scenario: Verify details are shown when open a trip
    Given I launch ARRIVE Console application
    And I accept notifications alert
    And I confirm that current site is selected
    When I tap on 'View Trips' button
    Then I saw site header name and current open trips without map
    And I am on arriveConsole home screen