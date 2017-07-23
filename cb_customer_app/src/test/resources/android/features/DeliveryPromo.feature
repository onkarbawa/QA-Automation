@deliveryPromo
Feature: Android - Delivery Promo code

  Scenario: Setting test environment
    Given I launch Curbside application
    And I am currently in 'Palo Alto, CA' city
    And I have selected test environment

  #TODO Promocodes are not working
  @Android @C114991
  Scenario Outline: Add delivery promo
    Given I am not signed into application
    And I sign in into application using username "fusic.test1@gmail.com" and password "fusic@123"
    And I add any product to the cart from store
    And I tap on 'Cart' button
    And I tap on 'Curbside Pickup' button
    And I tap on 'Delivery with UBER ' button
    And I select the delivery address as, street:"Palo Alto Square", city:"Palo Alto",state:"CA"
    And I tap on back button
    And I tap on Enter promo code link
    When I apply promo code "<Promo Code>"
    Then I should see promo code is applied and discount is given as per "<Discount Type>"
  Examples:
    |Promo Code   |Discount Type|
    |NF_DOLLAR_DS |Dollar       |
    #|NF_PERCENT_DS|Percent      |
    #|NF_FREE_DS   |Free         |
    #|NF_FIXED_DS  |Fixed        |