@appLaunch1
Feature: iOS- Application Launch 1

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected Experimental test environment
    And I am not signed into application

  @iOS @C114996
  Scenario: Verify Disable location services functionality
    Given 'Location' preference is set as 'Never' for 'Curbside' app
    And I verify that Location 'Never' is set

  Scenario Outline: Place Order
    When I launch Curbside application
    And I am on Home Screen
    Then I should see Location as 'Palo Alto'
    And I Sign-in with '<Email>' and '<Password>'
    And I saw email on MyAccount page
    And My cart is empty
    And I select 'Mock' retailer and search for 'Food'
    And I select 'Frozen food' product from list
    And I add 1 quantity of the product
    And I go to Cart screen
    And I attempt to place an order
    And I should see checkout not allowed
    And 'Location' preference is set as 'Always' for 'Curbside' app
    And I verify that Location 'Always' is set
    And I launch Curbside application
    And I am on Home Screen
    And I tap on 'Shop' icon in bottom menu
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    Then I should see checkout screen
    Examples:
      | Email              | Password  |
      | applaunch@exam.com | applaunch |

  @iOS @C114997
  Scenario: Verify Disable background application refresh functionality
    Given I turn 'OFF' Background App Refresh for 'Curbside' app
    And I verify that Background App Refresh is 'OFF'
    And I launch Curbside application
    And I am on Home Screen
    And My cart is empty
    And I select 'Palo Alto' from list
    And I select 'Mock' retailer and search for 'Food'
    And I select 'Frozen food' product from list
    And I add 1 quantity of the product
    And I go to Cart screen
    And I attempt to place an order
    And I should see checkout not allowed
    And I turn 'ON' Background App Refresh for 'Curbside' app
    And I verify that Background App Refresh is 'ON'
    And I launch Curbside application
    And I am on Home Screen
    And I tap on 'Shop' icon in bottom menu
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    Then I should see checkout screen

