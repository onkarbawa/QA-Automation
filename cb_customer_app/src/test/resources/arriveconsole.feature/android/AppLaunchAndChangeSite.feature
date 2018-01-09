@applicationLaunch
Feature: Android | ArriveConsole | AppLaunchAndChangeSite

  Scenario: Setting up the environment | Remove Previous trips
    Given I launch ARRIVE Console application for the first time
    And I remove all previous trips
    And I launch ARRIVE Console Tester application for the first time
    And I generate 2 sample trips

  @android @TCS01
  Scenario: Verify current site name and open trips are displayed without map
    Given I launch ARRIVE Console application
    Then I saw site header name and current open trips without map

  @android @TCS02
  Scenario: Verify current site name and open trips are displayed with map
    Given I am on arriveConsole home screen
    And I confirm that current site is selected
    When I tap on 'VIEW TRIPS' button
    Then I saw site header name and current open trips with map