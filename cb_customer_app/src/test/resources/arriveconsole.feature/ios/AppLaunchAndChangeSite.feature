@appLaunch
Feature: iOS | ArriveConsole | AppLaunchAndChangeSite

  @iOS @TCS01
  Scenario: Verify current site name and open trips are displayed without map
    Given I launch ARRIVE Console application for the first time
    And I accept notifications alert
    And I confirm that current site is selected
    When I tap on 'View Trips' button
    Then I saw site header name and current open trips without map
    And I am on arriveConsole home screen

  @iOS @TCS02
  Scenario: Verify current site name and open trips are displayed with map
    Given I confirm that current site is selected
    When I tap on 'View Trips' button
    Then I saw site header name and current open trips with map
    And I am on arriveConsole home screen

  @iOS @TCS03
  Scenario: Verify Change site functionality
    Given I confirm that current site is selected
    And I tap on 'View Trips' button
    And I saw site header name and current open trips with map
    When I tap on 'ic home' button
    Then I saw alert message
    And I tap on 'Change Site' button
    Then I confirm that after site switched move back to the same site
    And I tap on 'Choose a Different Site' button
    And I select a different site from list