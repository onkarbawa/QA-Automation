@mailVerification
Feature: iOS- Order flow verification

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment
    And I am not signed into application

  @iOS @C114951
  Scenario: Verify that cancelled order should appear in the Cancelled section
    Given I Sign-in with 'order_flow@curbside.com' and 'curbside'
    And My cart is empty
    And I select 'Westfield Valley Fair, at 2855 Stevens Creek Blvd' retailer and search for 'Salmon Oil'
    And I select 1no product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order