@applicationLaunch
Feature: Curbside tutorial is prompted when app is launched first time and verify tutorial flow

  @C114937
  Scenario: Tapping 'Get Started' button should take you to Store Selection Page
    Given I launch Curbside application for the first time
    And I am currently in 'Palo Alto, CA' city
    And I wait for 'Skip Intro' button
    And I swipe left 2 times on intro page
    And I tap on 'Get Started' button
    And I tap on 'OK' button on 'location access' page
    When I accept location access alert
    Then I should see 'Nearby stores' landing page

  @C114936
  Scenario: Tapping 'Skip Intro' button should take you to Store Selection Page
    Given I launch Curbside application for the first time
    And I am currently in 'Palo Alto, CA' city
    And I wait for 'Skip Intro' button
    And I tap on 'Skip Intro' button on intro page
    And I tap on 'OK' button on 'location access' page
    When I accept location access alert
    Then I should see 'Nearby stores' landing page
    
  @C114998
  Scenario: Verify Disable location services functionality
    Given 'Location' preference is set as 'OFF' for 'Curbside' app
    When I launch Curbside application
    And I am currently in 'Palo Alto, CA' city
    And I accept location access alert
    Then I should see 'Nearby stores' landing page