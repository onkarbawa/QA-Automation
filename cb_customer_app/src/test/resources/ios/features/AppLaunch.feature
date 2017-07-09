@appLaunch
Feature: Application Launch

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

  @C114936
  Scenario: Verify tap on 'Skip Intro' button should take you back to the Store Selection Screen
    Given I launch Curbside application
    And I tap on 'HelpIconWhite' button
    When I tap on 'Skip Intro' button
    Then I should see 'Nearby stores' landing page
    When I tap on 'HelpIconWhite' button
    And I swipe left 2 times
    When I tap on 'Get Started' button
    Then I should see 'Nearby stores' landing page

  @C114996
  Scenario: Verify Disable location services functionality
    Given 'Location' preference is set as 'Never' for 'Curbside' app
    When I launch Curbside application
    Then I should see Location Services Disabled screen
    When I tap on 'Settings' button
    And I set 'Location' permission as 'Always'
    And I launch Curbside application
    Then I should see 'Nearby stores' landing page

  @C114997
    Scenario: Verify Disable background application refresh functionality
      Given I turn 'OFF' Background App Refresh for 'Curbside' app
      When I launch Curbside application
      And I add any product to cart in 'Palo Alto' location
      And I go to Cart screen
      And I attempt to place an order
      Then I should see checkout not allowed
      When I turn 'ON' 'Background App Refresh' for 'Curbside' app
      And I launch Curbside application
      And I tap on 'Cart' icon in bottom menu
      And I tap on 'Place Order' button
      Then I should see checkout screen
    
