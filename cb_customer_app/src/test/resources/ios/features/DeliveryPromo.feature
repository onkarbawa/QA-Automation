@deliveryPromo
Feature: iOS - Delivery Promo code

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114991
  Scenario Outline: Add delivery promo
    Given I am not signed into application
    And I Sign-in with '<Email>' and '<Password>'
    And My cart is empty
    And I select 'Westfield Valley Fair, at 2855 Stevens Creek Blvd' retailer and search for 'Salmon Oil'
    And I select 1no product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I tap on 'Curbside Pickup' button
    And I tap on 'Delivery with UBER ' button