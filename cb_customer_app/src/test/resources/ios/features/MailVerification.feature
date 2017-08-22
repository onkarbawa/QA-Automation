@mailVerification
Feature: iOS- Mail verification flow

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment
    And I am not signed into application

  @iOS @C114978
  Scenario: Verify Mail subject - welcome to curbside
    Given I tap on 'My Account' icon in bottom menu
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    Then I should receive 'Welcome' Email from Curbside app
    And I am not signed into application
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114979
  Scenario: Verify Mail subject - We're Prepping Your Curbside Pickup Order(without Delivery Promo Code)
    Given I Sign-in with 'palo_alto@mailinator.com' and 'curbside'
    And My cart is empty
    And I select 'Mock Picking, at 260 Sheridan Ave' retailer and search for 'Refrigrated Food'
    And I select 'Food Item' product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    Then I should receive 'In-Progress' Email from Curbside app

  @iOS @C114983
  Scenario: Verify Mail subject - We're Prepping Your Curbside Pickup Order (With Delivery Promo Code)
    Given I am on Shop Screen
    And I select 'Food Item' product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I select Curbside Pickup and delivery option
    And I select the delivery address as:"4 Palo Alto Square,Palo Alto,CA"
    And I tap on back button
#    And I tap on 'Enter Promo Code'
#    And I apply 'NF_DOLLAR_DS' promo code
    And I attempt to place an order
    Then I should receive 'Delivery-Order' Email from Curbside app