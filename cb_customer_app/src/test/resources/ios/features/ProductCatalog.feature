@ProductCatalog
Feature: Verify store display functionality

  @C114941
  Scenario: Verify products display in landing page
    Given I launch Curbside application for the first time
    And I am on Home Screen
    And I have selected test environment
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select a store
    And I select 'Newton' retailer partner on Stores screen
    Then I should see following products listed on partner screen
    | product	| product_image	|
    | Perfume	| test_perfume.png	|
    
  @C114942 @C114944
  Scenario: Verify product landing page
    Given I launch Curbside application
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select a store
    When I tap on product from the list
    Then I should see product details as below

    
  @C114943
  Scenario: Verify products variant
    Given I launch Curbside application
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select a store
    And I tap on product from the list
    When I add product in cart
    Then I saw added product in cart