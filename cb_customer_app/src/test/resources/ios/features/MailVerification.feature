@mailVerification
Feature: iOS- Mail verification flow

  Scenario: Setting test environment
    Given I launch Curbside application for the first time
    And I have selected test environment
    And I am not signed into application
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114978
  Scenario: Verify Mail subject - welcome to curbside
    Given I tap on 'My Account' icon in bottom menu
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    Then I should receive 'Welcome' Email from Curbside app

  @iOS @C114979
  Scenario: Verify Mail subject - We're Prepping Your Curbside Pickup Order
    Given I add credit card information
    And I select 'Westfield Valley Fair, at 2855 Stevens Creek Blvd' retailer and search for 'Salmon Oil'
    And I select 1no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    Then I should receive 'In-Progress' Email from Curbside app