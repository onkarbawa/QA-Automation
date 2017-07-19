@ProductCatalog
Feature: Android- Product Catalog

  # TODO We can use "I am not signed into application" instead of "My cart is empty"
  # As feature file does not required any sign in
  # @Anil review
  Scenario: Setting test environment
    Given I launch Curbside application
    And I am currently in 'Palo Alto, CA' city
    And I have selected test environment
    And My cart is empty
    
  @Android @C114941
  Scenario: Verify products display in landing page
    Given I am on Home Screen
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