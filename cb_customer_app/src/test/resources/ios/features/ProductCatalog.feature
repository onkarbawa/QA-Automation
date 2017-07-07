@ProductCatalog
Feature: Verify store display functionality

  @C114941
  Scenario: Verify products display in landing page
    Given I launch Curbside application for the first time
    And I have selected test environment
    And I am on 'Stores' Screen
    And I select 'Metro Areas' > 'Boston' location
    And I select 'Newton' retailer partner on Stores screen
    Then I should see following products listed on partner screen
    | product	| product_image	|
    | Perfume	| test_perfume.png	|
    
  @C114942 @C114944
  Scenario: Verify product landing page
    When I tap on '<product>' product's title from the list
    Then I should see product details as below
    | product 	| product_image | SKU	| description		| overview	|
    | Perfume	| image.png		| khg	| This is perfume	| Excellent product by every meaning	|
    
  @C114943
  Scenario: Verify products variant
    Given I am on Signed Into application
    And I am on 'Stores' Screen
    And I select 'Metro Areas' > 'Boston' location
    And I select 'Newton' retailer partner on Stores screen
    And I search for 'Perfume' product
    And I tap on 'Perfume' product's image from the list
    And I select a product variant 'variant'
    Then I should see selected variant 'variant' in cart