@deliveryPromo
Feature: Android - Delivery Promo code

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment

  @Android @C114949
  Scenario Outline: Add delivery promo code
    Given I am not signed into application
    And I sign in into application using username "promocode@test.com" and password "1234567890"
    And I am on 'Palo Alto' location 'Stores' Screen
    And I tap on 'Cart' button
    And I remove and add the product again to the cart
    And I tap on 'Curbside Pickup' button
    And I tap on Delivery with UBER button
    And I select the delivery address as, street:"Palo Alto Square", city:"Palo Alto",state:"CA"
    And I tap on back button
    And I store the value of 'Delivery Charges'
    And I tap on Enter promo code link
    When I apply promo code "<Promo Code>"
    Then I should see promo code is applied and discount is given as per '<Discount Type>'
    Examples:
      |Promo Code   |Discount Type|
      |UNLIMITED    |Unlimited    |

  @Android @C114991
  Scenario Outline: verify Dollar promo code on Delivery charges
    And I tap on Enter promo code link
    When I apply promo code "<Promo Code>"
    Then I should see promo code is applied and discount is given as per '<Discount Type>'
    Examples:
      |Promo Code   |Discount Type     |
      |NF_DOLLAR_DS | Dollar-Delivery  |

  @Android @C114992
  Scenario Outline: verify percentage promo code on Delivery charges
    And I tap on Enter promo code link
    When I apply promo code "<Promo Code>"
    Then I should see promo code is applied and discount is given as per '<Discount Type>'
    Examples:
      |Promo Code    |Discount Type     |
      |NF_PERCENT_DS | Percent-Delivery |

  @Android @C114993
  Scenario Outline: verify Free promo code on Delivery charges
    And I tap on Enter promo code link
    When I apply promo code "<Promo Code>"
    Then I should see promo code is applied and discount is given as per '<Discount Type>'
    Examples:
      |Promo Code |Discount Type    |
      |NF_FREE_DS | Free-Delivery   |

  @Android @C114994
  Scenario Outline: verify Free promo code on Delivery charges
    And I tap on Enter promo code link
    When I apply promo code "<Promo Code>"
    Then I should see promo code is applied and discount is given as per '<Discount Type>'
    Examples:
      |Promo Code  |Discount Type    |
      |NF_FIXED_DS | Fixed-Delivery  |
