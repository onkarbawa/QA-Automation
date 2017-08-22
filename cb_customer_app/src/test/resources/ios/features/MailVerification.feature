@mailVerification
Feature: iOS- Mail verification flow

  Scenario: Setting test environment
    Given I launch Curbside application
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
    And I am not signed into application

  @iOS @C114979
  Scenario: Verify Mail subject - We're Prepping Your Curbside Pickup Order(without Delivery Promo Code)
    Given I Sign-in with 'curb@mailinator.com' and 'curbside'
    And I select 'Mock Picking, at 260 Sheridan Ave' retailer and search for 'Refrigrated Food'
    And I select 'Food Item' product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    Then I should receive 'In-Progress' Email from Curbside app

  @iOS @C114983
  Scenario: Verify Mail subject - We're Prepping Your Curbside Pickup Order (With Delivery Promo Code)
    Given I tap on 'Shop' icon in bottom menu
    And I select 1no product from list
    And I add 1 quantity of the product
