@endToEndVerification
Feature: iOS- End to End flow verification

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected Experimental test environment
    And I am not signed into application
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114962
  Scenario: End to End - Signup EMail, Signup SMS and Pickup order EMail verifications
    And I select 'Mock' retailer and search for 'Food'
    And I select 'Frozen food' product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on 'Create An Account' button
    And I check there is no latest SMS from Curbside
    And I signup for a new account
    And I will receive welcome SMS from Curbside
    Then I should receive 'Welcome' Email from Curbside app
    And I add credit card
    And I add loyality card information
    And I go to Cart screen
    And I check there is no latest SMS from Curbside
    When I attempt to place an order
    Then I should see checkout screen
    Then I will receive order SMS from Curbside
    Then I should receive 'Progress' Email from Curbside app


  @iOS @C114963
  Scenario: Verify Mail subject - We're Prepping Your Curbside Pickup Order(without Delivery Promo Code)
    Given I am not signed into application
    And I Sign-in with 'live_order@mailinator.com' and 'curbside'
    And I saw email on MyAccount page
    And My cart is empty
    And I select 'Mock' retailer and search for 'Food'
    And I select 'Frozen food' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I tap on 'Enter Promo Code'
    And I apply 'UNLIMITED' promo code
    And I checked threshold value for Promo code
    And I verify discount is applied
    When I attempt to place an order
    Then I should see checkout screen
    Then I should receive 'In-Progress' Email from Curbside app

  @iOS @C114964
  Scenario: Verify Mail subject - We're Prepping Your Curbside Pickup Order (With Delivery Promo Code)
    Given I am on Shop Screen
    And My cart is empty
    And I select 'Mock' retailer and search for 'Food'
    And I select 'Frozen food' product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I select Curbside Pickup and delivery option
    And I select the delivery address as:"4 Palo Alto Square,Palo Alto,CA"
    And I tap on back button
    When I attempt to place an order
    Then I should see checkout screen
    Then I should receive 'Delivery-Order' Email from Curbside app
