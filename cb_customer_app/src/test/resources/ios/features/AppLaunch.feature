@appLaunch
Feature: Verify application functionality on welcome screen
  @C114937
  Scenario: First time Curbside Launch (fresh install)
    Given I launch Curbside application for the first time
    And I accept notifications alert
    And I swipe left 2 times
    And I tap on 'Get Started' button
    And I tap on 'OK with me' button on 'location access' page
    When I accept location access alert
    Then I should see 'Nearby stores' landing page 
    And 'Location' preference should be set as 'Always' for 'Curbside' app
