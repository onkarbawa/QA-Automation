@ProductCatalog
Feature: Android- Product Catalog

  Scenario: Setting test environment
    Given I launch Curbside application
    And I am currently in 'Palo Alto, CA' city
    And I have selected test environment
    
  @Android @C114941
  Scenario: Verify products display in landing page
    Given I am not signed into application
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select a store
    Then I should see following products listed on partner screen

  @Android @C114942 @C114944
  Scenario: Verify product landing page
    Given I tap on product from the list
    Then I should see product details as below

  @Android @C114943
  Scenario: Verify products variant
    Given I add product in cart
    Then I saw added product in cart