@cartBuilding
Feature: iOS- Cart Building And Checkout

  Scenario: Setting test environment
    Given I launch Curbside application for the first time
    And I have selected test environment
    And I am not signed into application
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114945
  Scenario Outline: : Verify products display in landing page
    Given I Sign-in with '<Email>' and '<Password>'
    And I saw email on MyAccount page
    And My cart is empty
    And I select 'Mock Picking, at 260 Sheridan Ave' retailer and search for 'Refrigrated Food'
    And I select 1no product from list
    And I add 2 quantity of the product
    And I select 2no product from list
    And I add 1 quantity of the product
    When I tap on 'Cart' icon in bottom menu
    Then I should see the 3 items in the cart
    Examples:
      |             Email                  |     Password    |
      |      ioscart@exam.com              |     curbside    |

  @iOS @C114947
  Scenario: Verify product pricing are correct
    Then I should see '26.47' dollar in the cart

  @iOS @C114946
  Scenario: Verify math and calculations are correct
    Then I should see added product total amount

  @iOS @C114990 @C115039
  Scenario: Verify math calculations are correct as per Promo Code
    Given I tap on 'Enter Promo Code'
    And I apply 'UNLIMITED' promo code
    When I verify discount is applied
    And I tap on back button
    Then I go for place order


