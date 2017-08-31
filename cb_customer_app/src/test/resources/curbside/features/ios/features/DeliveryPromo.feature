@deliveryPromo
Feature: iOS - Delivery Promo code

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment
    And I am not signed into application
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114991
  Scenario Outline: Verify Delivery Promo - $
    Given I Sign-in with 'delivery_promo@curbside.com' and 'curbside'
    And My cart is empty
    And I select 'Mock Picking, at 260 Sheridan Ave' retailer and search for 'Refrigrated Food'
    And I select 'Food Item' product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I select Curbside Pickup and delivery option
    And I select the delivery address as:"4 Palo Alto Square,Palo Alto,CA"
    And I tap on back button
    And I store the value of 'Delivery Charges'
    And I tap on 'Enter Promo Code'
    When I apply '<Promo Code>' promo code
    Then I should see delivery promo code is applied and discount is given as per '<Discount Type>'
    Examples:
      |Promo Code   |Discount Type|
      |NF_DOLLAR_DS_NEW  |  Dollar     |

  @iOS @C114992
  Scenario Outline: Verify Delivery Promo - %
    Given I tap on 'Enter Promo Code'
    When I apply '<Promo Code>' promo code
    Then I should see delivery promo code is applied and discount is given as per '<Discount Type>'
    Examples:
      |Promo Code       |Discount Type|
      |NF_PERCENT_DS |   DeliveryPercent   |

  @iOS @C114993
  Scenario Outline: Verify Delivery Promo - Free
    Given I tap on 'Enter Promo Code'
    When I apply '<Promo Code>' promo code
    Then I should see delivery promo code is applied and discount is given as per '<Discount Type>'
    Examples:
      |Promo Code       |Discount Type|
      |NF_FREE_DS|   Free   |

  @iOS @C114994
  Scenario Outline: Verify Delivery Promo - Fixed
    Given I tap on 'Enter Promo Code'
    When I apply '<Promo Code>' promo code
    Then I should see delivery promo code is applied and discount is given as per '<Discount Type>'
    Examples:
      |Promo Code       |Discount Type|
      |NF_FIXED_DS|   Fixed   |

  @iOS @C114949
  Scenario Outline: verify Repeat Promo codes
    Given I tap on 'Enter Promo Code'
    When I apply '<Promo Code>' promo code
    Then I should see repeat promo code is applied and discount is given as per '<Discount Type>'
    Examples:
      |Promo Code       |Discount Type|
      |UNLIMITED |   Unlimited   |
