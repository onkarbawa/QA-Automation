@myAccount
Feature: iOS- Account flow Cards verification

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment
    And I am not signed into application
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114960
  Scenario: Verify that Credit Card Add/Remove
    Given I am on Sign Up screen
    And I signup for a new account
    And I add credit card information
    Then I should see credit info on payment info screen
    And I add any product to cart in 'Palo Alto' location
    When I go to Cart screen
    Then I should see credit info on cart screen
    Then I removed added 'Credit Card' from 'Payment Info' Screen

  @iOS @C114961
  Scenario: Verify that Loyality Card Add/Remove
    Given I add loyality card information
    Then I should see ExtraCare Card info on Loyalty Cards screen
    When I go to Cart screen
    Then I should see loyalty card info on cart screen
    Then I removed added 'Loyality Card' from 'Loyality Card' Screen