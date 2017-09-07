@appLaunch1
Feature: iOS- Application Launch 1

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment
    And I am not signed into application

  @iOS @C114996
  Scenario Outline: Verify Disable location services functionality
    Given 'Location' preference is set as 'Never' for 'Curbside' app
    And I verify that Location 'Never' is set
    When I launch Curbside application
    Then I should see Location as 'Palo Alto'
    And I Sign-in with '<Email>' and '<Password>'
    And I saw email on MyAccount page
    And My cart is empty
    And I select 'CVS, at 855 El Camino Real' retailer and add any product to cart
    And I go to Cart screen
    And I attempt to place an order
    And I should see checkout not allowed
    And I set 'Location' permission as 'Always'
    And I tap on 'Return to Curbside' button
    And I tap on 'Shop' icon in bottom menu
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    Then I should see checkout screen
  Examples:
  |             Email                  |     Password    |
  |      applaunch@exam.com            |     applaunch    |

  @iOS @C114997
  Scenario: Verify Disable background application refresh functionality
    Given I turn 'OFF' Background App Refresh for 'Curbside' app
    And I launch Curbside application
    And My cart is empty
    And I select 'CVS, at 855 El Camino Real' retailer and add any product to cart
    And I go to Cart screen
    And I attempt to place an order
    And I should see checkout not allowed
    When I turn 'ON' 'Background App Refresh' for 'Curbside'
    And I tap on 'Return to Curbside' button
    And I tap on 'Shop' icon in bottom menu
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    Then I should see checkout screen

