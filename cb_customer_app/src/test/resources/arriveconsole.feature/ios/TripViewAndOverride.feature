@tripView
Feature: iOS | ArriveConsole | Trip View and OverRide

  @iOS @TCS04
  Scenario: Verify details are shown when open a trip
    Given I launch ARRIVE Console application
    And I accept notifications alert
    And I select site which have open trips
    When I tap on open trip
    Then I saw open trip details
    And I am on arriveConsole home screen

  @iOS @TCS07
  Scenario: Verify manual added location is displayed on home screen
    Given I launch ARRIVE Console application
    And I accept notifications alert
    And I confirm that current site is selected
    And I switch application to debug mode
    And I tap on 'Search For Sites' button
    And I selected random site from map
    And I confirmed selected site
    And I tap on 'Apply' button
    And I tap on 'OK' button
    And I checked selected site is shown on bottom of the screen

