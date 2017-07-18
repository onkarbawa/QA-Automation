@cartBuilding
Feature: Android- Cart Building

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment

  @Android @C114941
  Scenario: Verify products display in landing page
    Given I signin in using signup information
    And My cart is empty
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select a store
    And I tap on product from the list
    And I add product in cart
    And I saw added product in cart