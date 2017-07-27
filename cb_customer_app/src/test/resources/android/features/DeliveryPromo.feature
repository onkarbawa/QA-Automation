@deliveryPromo
Feature: Android - Delivery Promo code

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment

  #TODO Promocodes are not working
  @Android @C114991
  Scenario Outline: Add delivery promo code
    Given I am not signed into application
    And I sign in into application using username "promocode@test.com" and password "1234567890"
    And I am on 'Palo Alto' location 'Stores' Screen
    And My cart is empty
    And I select 'CVS' store and search for 'children claritin non 30oct' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    And I tap on 'Curbside Pickup' button
    And I tap on 'Delivery with UBER ' button
    And I select the delivery address as, street:"Palo Alto Square", city:"Palo Alto",state:"CA"
    And I tap on back button
    And I tap on Enter promo code link
    When I apply promo code "<Promo Code>"
    Then I should see promo code is applied and discount is given as per '<Discount Type>'
  Examples:
    |Promo Code   |Discount Type|
    |UNLIMITED    |Unlimited    |
    #|NF_DOLLAR_DS |Dollar      |
    #|NF_PERCENT_DS|Percent     |
    #|NF_FREE_DS   |Free        |
    #|NF_FIXED_DS  |Fixed       |


  Scenario Outline: verify percentage promo code
    And I tap on Enter promo code link
    When I apply promo code "<Promo Code>"
    Then I should see promo code is applied and discount is given as per '<Discount Type>'
  Examples:
  |Promo Code       |Discount Type|
  |REPEAT_PERCENT_S |   Percent   |