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
    And I tap on open trip
    And I tap on cancel trip button
    And I saw cancel alert message
    When I tap on 'YES, CANCEL TRIP' button
    Then I saw trip removed from the list