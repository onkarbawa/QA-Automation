@sms
Feature: iOS- SMS Flow

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected Experimental test environment
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114977
  Scenario: Verify SMS - welcome on new user Sign-Up
    Given I check there is no latest SMS from Curbisde
    And I tap on 'My Account' icon in bottom menu
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    And I should receive welcome SMS from Curbside

  @iOS @C114969
  Scenario: Verify SMS is received after place order
    Given I add credit card information
    And I check there is no latest SMS from Curbisde
    And I select 'Mock' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I should receive order SMS from Curbside
