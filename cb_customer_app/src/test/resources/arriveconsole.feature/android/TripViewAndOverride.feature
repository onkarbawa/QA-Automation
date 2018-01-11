@applicationLaunch
Feature: Android | ArriveConsole | Trip View and OverRide

  @android @TCS04
  Scenario: Verify details are shown when open a trip
    Given I launch ARRIVE Console application
    And I accept notifications alert
    And I select site which have open trips
    When I tap on open trip
    Then I saw open trip details
    And I am on arriveConsole home screen