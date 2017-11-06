@signUp
Feature: iOS- Signup Flow

  Scenario: Setting test environment
#    Given I turn 'ON' 'Background App Refresh' and 'Allow Notifications' for 'Curbside' app
    Given I launch Curbside application
    And I have selected test environment
    
  @iOS @C114954 @C114957
  Scenario: Verify on SignUp user should see name, email or phone number in My Account screen
    Given I am not signed into application
    And I tap on 'My Account' icon in bottom menu
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    Then I should see my signup information under Account Info

  @iOS @C114958
  Scenario Outline: Add credit card
    Given I tap on 'My Account' icon in bottom menu
    And I tap on 'Payment Info' button
    And I tap on 'Add New Card' button
    And I add credit card information as '<first_name>', '<last_name>', '<card_number>', '<card_expiry>', '<card_cvv>', '<card_address1>', '<card_address2>', '<card_city>', '<card_state>', '<card_zip>'
    Then I should see credit info on payment info screen
    And I add any product to cart in 'Palo Alto' location
    When I go to Cart screen
    Then I should see credit info on cart screen

    Examples:
      | first_name | last_name | card_number      | card_expiry | card_cvv |card_address1 | card_address2 | card_city | card_state | card_zip |
      | Test       | Data      | 6011111111111117 | 1120        | 345      |   TestData   |    Test data  |   Boston  |            |   56635  |

  @iOS @C114959
  Scenario: Add loyalty card
    Given I tap on 'My Account' icon in bottom menu
    And I tap on 'Loyalty Cards' button
    And I tap on 'Add New Card' button
    And I add an ExtraCare Card numbered '87676478652876'
    Then I should see ExtraCare Card info on Loyalty Cards screen
    When I go to Cart screen
    Then I should see loyalty card info on cart screen

