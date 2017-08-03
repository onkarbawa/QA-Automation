@test
Feature: iOS- Application Launch

  @iOS @C114997
  Scenario Outline: Verify Disable background application refresh functionality
    Given I turn 'OFF' Background App Refresh for 'Curbside' app
    And I launch Curbside application for the first time
    And I have selected test environment
    And I am on 'Palo Alto' location 'Stores' Screen
    And I am not signed into application
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
    And I am not signed into application
    Examples:
      |             Email                  |     Password    |
      |      applaunch@exam.com            |     applaunch    |

  @iOS @C114954
  Scenario: Verify on SignUp user should see name, email or phone number in My Account screen
    Given I am not signed into application
    And I tap on 'My Account' icon in bottom menu
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    Then I should see my signup information under Account Info
