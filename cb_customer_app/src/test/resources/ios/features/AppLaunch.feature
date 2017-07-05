@appLaunch
Feature: Verify application functionality on welcome screen
  @C114937
  Scenario: First time Curbside Launch (fresh install)
    Given I launch Curbside application
    And I accept notifications alert
    And I swipe left 2 times
    And I tap on 'Get Started' button
    And I tap on 'OK with me' button on 'location access' page
    When I accept location access alert
    Then I should see 'Nearby stores' landing page 
    And 'Location' preference should be set as 'Always' for 'Curbside' app

  @C114936
  Scenario: Verify tap on 'Skip Intro' button should take you back to the Store Selection Screen
    Given I launch Curbside application
    And I accept notifications alert
    And I tap on 'Skip Intro' button
    And I tap on 'HelpIconWhite' button
    And I swipe left 2 times
    When I tap on 'Get Started' button
    Then I should see 'Nearby stores' landing page

  @C114996
  Scenario: Verify Disable location services functionality
    Given 'Location' preference is set as 'Never' for 'Curbside' app
    When I launch Curbside application
    And I accept notifications alert
    Then I should see Location Services Disabled screen
    When I tap on 'Settings' button
    And I tap on 'Location' button
    And I tap on 'Always' button
    When I launch Curbside application
    Then I should see 'Nearby stores' landing page



    
