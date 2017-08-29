@appLaunch1
Feature: iOS- Application Launch 1

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment
    And I am not signed into application
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114996
  Scenario: Verify Disable location services functionality
    Given 'Location' preference is set as 'Never' for 'Curbside' app
    And I verify that Location 'Never' is set
    When I launch Curbside application
    Then I should see Location Services Disabled screen
    When I tap on 'Settings' button
    And I set 'Location' permission as 'Always'
    And I tap on 'Return to Curbside' button
    Then I should see 'Nearby stores' landing page

  @iOS @C114997
  Scenario Outline: Verify Disable background application refresh functionality
    Given I turn 'OFF' Background App Refresh for 'Curbside' app
    And I launch Curbside application
    And I Sign-in with '<Email>' and '<Password>'
    And My cart is empty
    And I select 'CVS, at 855 El Camino Real' retailer and add any product to cart
    And I go to Cart screen
    And I attempt to place an order
    And I should see checkout not allowed
    Then I turn 'ON' 'Allow Notifications' for 'Curbside'
    And I tap on back button
    When I turn 'ON' 'Background App Refresh' for 'Curbside'
    And I tap on 'Return to Curbside' button
    And I tap on 'Shop' icon in bottom menu
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    Then I should see checkout screen
    Examples:
      |             Email                  |     Password    |
      |      applaunch@exam.com            |     applaunch    |
