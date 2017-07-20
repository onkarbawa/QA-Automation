@cartBuilding
Feature: Android- Cart Building
  Scenario: Setting test environment
    Given I launch Curbside application
    And I am currently in 'Palo Alto, CA' city
    And I have selected test environment


  #TODO @Anil please review this scenario once
  @Android @C114945
  Scenario Outline: Verify the item(s) quantity(ies) are correctly added into cart
    Given I am not signed into application
    And I am signed in with "<email>" and "<password>"
    And I am on 'Palo Alto' location 'Stores' Screen
    And My cart is empty
    And I search for 'carlson aces' product from 'Store' Screen
    And I select 1st product from list
    And I add 2 quantity of the product
    And I select 2nd product from list
    And I add 1 quantity of the product
    When I tap on 'Cart' button
    Then I should see the 3 items in the cart
    Examples:
      |       email         |      password     |
      |cartbuilding@test.com|  1234567890       |


  @Android @C114946
  Scenario: Verify math and calculations are correct
    Given I tap on 'Cart' button
    And I refresh the sub-store details if displayed
    Then I should see '53.29' dollars as total amount