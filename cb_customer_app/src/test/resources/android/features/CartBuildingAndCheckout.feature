@cartBuilding
Feature: Android- Cart Building
  Scenario: Setting test environment
    Given I launch Curbside application
    And I am currently in 'Palo Alto, CA' city
    And I have selected test environment

  @Android @C114945
  Scenario: Verify the item(s) quantity(ies) are correctly added into cart
    Given I am not signed into application
    And I sign in into application using username "cartbuilding@test.com" and password "1234567890"
    And I am on 'Palo Alto' location 'Stores' Screen
    And My cart is empty
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 2 quantity of the product
    And I am at 'CVS' store and search for 'Medicine' product
    And I select 1 product from list
    And I add 1 quantity of the product
    When I tap on 'Cart' button
    Then I should see the 3 items in the cart


  @Android @C114946
  Scenario: Verify math and calculations are correct
    Then I verify the total amount in the cart