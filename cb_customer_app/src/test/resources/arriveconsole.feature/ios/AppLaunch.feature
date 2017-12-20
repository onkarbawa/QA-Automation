@appLaunch
Feature: iOS | ArriveConsole | ApplicationLaunch

  @iOS @TCS01
  Scenario: Verify current site name and open trips are displayed
    Given I launch ARRIVE Console application for the first time
    And I accept notifications alert
    And I confirm that current site is selected
    When I tap on 'View Trips' button
    Then I saw site header name and current open trips without map