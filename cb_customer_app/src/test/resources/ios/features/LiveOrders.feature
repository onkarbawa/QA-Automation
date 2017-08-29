@endToEndVerification
Feature: iOS- End to End flow verification

  Scenario: Setting test environment
    Given I launch Curbside application 
    And I have selected test environment
    And I am not signed into application
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114962
  Scenario: Verify First time User place end to end order
    Given I select 'Mock Picking, at 260 Sheridan Ave' retailer and search for 'Refrigrated Food'
    And I select 'Food Item' product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on 'Create An Account' button
    And I signup for a new account
    And I add credit card
    And I add loyality card information
    And I go to Cart screen
    And I check there is no latest SMS from Curbisde
    Then I attempt to place an order
    Then I should receive welcome SMS from Curbside
