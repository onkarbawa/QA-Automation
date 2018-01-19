@mailVerification
Feature: iOS- Order flow verification

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected Experimental test environment
    And I am not signed into application
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114951
  Scenario: Verify that cancelled order should appear in the Cancelled section
    Given I Sign-in with 'order_flow@curbside.com' and 'curbside'
    And I saw email on MyAccount page
    And My cart is empty
    And I select 'Mock' retailer and search for 'Food'
    And I select 'Frozen food' product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I store the value of OrderId
    And I tap on 'Cancel This Order'
    And I tap on reason 'Changed my mind' for cancelling
    When I tap on 'Cancel Order'
    Then I should see cancel order information under Cancelled Order screen
    Then I should see cancel order in My Orders Screen
