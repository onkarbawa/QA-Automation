@cartBuilding
Feature: iOS- Cart Building And Checkout

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114945
  Scenario Outline: : Verify products display in landing page
    Given I am not signed into application
    And I Sign-in with '<Email>' and '<Password>'
    And My cart is empty
    And I select 'Westfield Valley Fair' retailer and search for 'oil'
    And I select 1no product from list
    And I add 2 quantity of the product
    And I select 2no product from list
    And I add 1 quantity of the product
    When I tap on 'Cart' icon in bottom menu
    Then I should see the 3 items in the cart
    Examples:
      |             Email                  |     Password    |
      |      fusic.test1@gmail.com         |     fusic@123  |

  @iOS @C114946
  Scenario: Verify math and calculations are correct
    Then I should see added product total amount


  @iOS @C114990
  Scenario: Verify products display in landing page
    Given I Sign-in with cart building credentials
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select a store
    And I tap on product from the list
    And I add product in cart
    And I saw added product in cart