@ProductCatalog
Feature: iOS- Product Catalog

  Scenario: Setting test environment 
    Given I launch Curbside application
    And I have selected Experimental test environment
    
  @iOS @C114941
  Scenario: Verify products display in landing page
    Given I am on Home Screen
    And I am on 'Palo Alto' location 'Stores' Screen
    When I select 'CVS' retailer
    Then I should see following products listed on partner screen

  @iOS @C114942 @C114944
  Scenario: Verify product landing page
    Given I tap on product from the list
    Then I should see product details as below

  @iOS @C114943
  Scenario: Verify products variant
    Given I add product in cart
    Then I saw added product in cart
    And My cart is empty