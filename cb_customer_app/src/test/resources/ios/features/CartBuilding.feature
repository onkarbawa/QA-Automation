@cartBuilding
Feature: iOS- Cart Building

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment

  @iOS @C114945
  Scenario: Verify products display in landing page
    Given I Sign-in with cart building credentials
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select a store
    And I tap on product from the list
    And I add product in cart
    And I saw added product in cart

  @iOS @C114946
  Scenario: Verify math and calculations are correct
    Given I am on 'Palo Alto' location 'Stores' Screen
    And I select a store
    And I tap on product from the list
    And I add product in cart

  @iOS @C114990
  Scenario: Verify products display in landing page
    Given I Sign-in with cart building credentials
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select a store
    And I tap on product from the list
    And I add product in cart
    And I saw added product in cart