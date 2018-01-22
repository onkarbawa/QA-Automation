@tripView
Feature: Android | ArriveConsole | TripView

  Scenario: Setting up the environment | Arrive Console
    Given I launch ARRIVE Console application
    And I am on arriveconsole_2 site
    And I generate 1 trip for arriveconsole_2 site

  @android @TCS04
  Scenario: Verify details are shown when open a trip
    When I tap on open trip
    Then I saw open trip details