@appLaunchAndChangeSite
Feature: iOS | ArriveConsole | AppLaunchAndChangeSite

  @iOS @TCS01
  Scenario: Verify current site name and open trips are displayed without map
    Given I launch ARRIVE Console application for the first time
    And I accept notifications alert message
    And I confirm that current site is selected
    And I tap on 'View Trips' button
    When I add open trips for arriveConsole app
    Then I saw site header name and current open trips without map

  @iOS @TCS02
  Scenario: Verify current site name and open trips are displayed with map
    Given I am on arriveConsole home screen
    And I confirm that current site is selected
    When I tap on 'View Trips' button
    Then I saw site header name and current open trips with map

  @iOS @TCS03
  Scenario: Verify Change site functionality
    Given I tap on 'ic home' button
    And I saw alert message
    And I tap on 'Change Site' button
    And I confirm that after site switched move back to the same site
    When I tap on 'Choose a Different Site' button
    Then I select a different site from list