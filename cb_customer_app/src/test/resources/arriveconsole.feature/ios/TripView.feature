@tripView
Feature: iOS | ArriveConsole | Trip View

  @iOS @TCS04
  Scenario: Verify details are shown when open a trip
    Given I launch ARRIVE Console application
    And I accept notifications alert
    And I select site which have open trips
    When I tap on trip
    Then I saw open trip details

  @iOS @TCS04
  Scenario: Verify details are shown when open a trip
    Given I launch ARRIVE Console application
    And I accept notifications alert
    And I select site which have open trips
