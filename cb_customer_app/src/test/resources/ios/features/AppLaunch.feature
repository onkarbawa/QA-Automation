@appLaunch
Feature: iOS- Application Launch

  Scenario: Setting test environment
    Given I turn 'ON' Background App Refresh for 'Curbside' app

  @iOS @C114937
  Scenario: First time Curbside Launch (fresh install)
    Given I launch Curbside application for the first time
    And I accept notifications alert
    And I swipe left 2 times
    And I tap on 'Get Started' button
    And I tap on 'OK with me' button on 'location access' page
    When I accept location access alert
    Then I should see 'Nearby stores' landing page
    And 'Location' preference should be set as 'Always' for 'Curbside' app

  @iOS @C114936
  Scenario: Verify tap on 'Skip Intro' button should take you back to the Store Selection Screen
    Given I launch Curbside application
    And I am on Home Screen
    And I tap on 'Help' button
    When I tap on 'Skip Intro' button
    Then I should see 'Nearby stores' landing page
    When I tap on 'Help' button
    And I swipe left 2 times
    When I tap on 'Get Started' button
    Then I should see 'Nearby stores' landing page
