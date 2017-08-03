@deliveryPromo
Feature: iOS - Delivery Promo code

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114948
  Scenario Outline: Verify Repeat Promo codes
    Given I am not signed into application
    And I am on Sign Up screen
    And I signup for a new account
    And I added credit card information
    And I select 'CVS, at 855 El Camino Real' retailer and search for 'Cvs Indoor/Outdoor'
    And I select 'Cvs Allergy Relief 24 Hour Indoor/outdoor' product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I select Curbside Pickup and delivery option
    And I select the delivery address as:"4 Palo Alto Square,Palo Alto,CA"
    And I tap on back button
    And I tap on 'Enter Promo Code'
    When I apply '<Promo Code>' promo code
    Then I should see promo code is applied and discount is given as per '<Discount Type>'
    Examples:
      |Promo Code   |Discount Type|
      | UNLIMITED   |Unlimited    |
    #|NF_DOLLAR_DS |Dollar      |
    #|NF_PERCENT_DS|Percent     |
    #|NF_FREE_DS   |Free        |
    #|NF_FIXED_DS  |Fixed       |
    #  |FIRSTTIME_S|  |

  @iOS @C114949
  Scenario Outline: verify Repeat Promo codes
    Given I tap on 'Enter Promo Code'
    When I apply '<Promo Code>' promo code
    Then I should see promo code is applied and discount is given as per '<Discount Type>'
    Examples:
      |Promo Code       |Discount Type|
      |REPEAT_PERCENT_S |   Percent   |
#
#  @iOS @C114949
#  Scenario Outline: verify Repeat Promo codes
#    Given I tap on 'Enter Promo Code'
#    When I apply '<Promo Code>' promo code
#    Then I should see promo code is applied and discount is given as per '<Discount Type>'
#    Examples:
#      |Promo Code       |Discount Type|
#      |REPEAT_S         | Unlimited   |
#
#  @iOS @C114949
#  Scenario Outline: verify Repeat Promo codes
#    Given I tap on 'Enter Promo Code'
#    When I apply '<Promo Code>' promo code
#    Then I should see promo code is applied and discount is given as per '<Discount Type>'
#    Examples:
#      |Promo Code       |Discount Type|
#      |REPEAT_CVS_S     | Unlimited   |
#
#  @iOS @C114949
#  Scenario Outline: verify Repeat Promo codes
#    Given I tap on 'Enter Promo Code'
#    When I apply '<Promo Code>' promo code
#    Then I should see promo code is applied and discount is given as per '<Discount Type>'
#    Examples:
#      |Promo Code         |Discount Type|
#      |REPEAT_NTIMES_S     | Unlimited   |
#
#  @iOS @C114949
#  Scenario Outline: verify Repeat Promo codes
#    Given I tap on 'Enter Promo Code'
#    When I apply '<Promo Code>' promo code
#    Then I should see promo code is applied and discount is given as per '<Discount Type>'
#    Examples:
#      |Promo Code              |Discount Type|
#      |REPEAT_CURBFRONT_S     | Unlimited   |


#    And I Sign-in with 'iospromocode@exam.com' and 'promocode'