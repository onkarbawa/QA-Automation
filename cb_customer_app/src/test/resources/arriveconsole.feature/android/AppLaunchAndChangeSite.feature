@applicationLaunch
Feature: Android | ArriveConsole | AppLaunchAndChangeSite

  Scenario: Setting up the environment | Arrive Console
    Given I launch ARRIVE Console application for the first time
    And I generate trips if not present

  @android @TCS01
  Scenario: Verify current site name and open trips are displayed without map
    Then I saw site header name and current open trips without map

  @android @TCS02
  Scenario: Verify current site name and open trips are displayed with map
    Given I am on arriveConsole home screen
    And I confirm that current site is selected
    When I tap on 'VIEW TRIPS' button
    Then I saw site header name and current open trips with map

  @android @TCS03
  Scenario: Verify Change site functionality
    Given I am on arriveConsole home screen
    And I confirm that current site is selected
    And I tap on 'VIEW TRIPS' button
    And I saw site header name and current open trips with map
    And I tap on home button
    And I saw alert message
    When I tap on change site button
    Then I confirm that after site switched move back to the same site
    When I tap on 'CHOOSE A DIFFERENT SITE' button
    Then I select a different site from list