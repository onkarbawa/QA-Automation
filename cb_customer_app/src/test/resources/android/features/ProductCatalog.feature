@ProductCatalog
Feature: Android- Product Catalog

  @C114941
  Scenario: Verify products display in landing page
    Given I launch Curbside application
    And I am on Home Screen
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select a store
    Then I should see following products listed on partner screen

  @C114942 @C114944
  Scenario: Verify product landing page
    Given I launch Curbside application
    And I am on Home Screen
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select a store
    When I tap on product from the list
    Then I should see product details as below

  @C114943
  Scenario: Verify products variant
    Given I launch Curbside application
    And I am on Home Screen
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select a store
    And I tap on product from the list
    When I add product in cart
    Then I saw added product in cart