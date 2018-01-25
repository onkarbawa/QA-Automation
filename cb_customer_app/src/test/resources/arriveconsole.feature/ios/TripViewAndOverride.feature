@tripView
Feature: iOS | ArriveConsole | Trip View and OverRide

  @iOS @TCS07
  Scenario: Verify manual added location is displayed on home screen
    Given I launch ARRIVE Console application
    And I am on arriveConsole home screen
    And I confirm that current site is selected
    And I switch application to debug mode
    And I tap on 'Search For Sites' button
    And I selected random site from map
    And I confirmed selected site
    And I tap on 'Apply' button
    When I tap on 'OK' button
    Then I checked selected site is shown on bottom of the screen

