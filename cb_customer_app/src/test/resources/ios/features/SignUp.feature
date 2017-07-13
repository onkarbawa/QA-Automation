@signUp
Feature: Signup
  @Example
  Scenario: Add loyalty card
    Given I launch Curbside application
    And My cart is empty
    And I add any product to cart in 'Palo Alto' location
    And My cart is empty

    @C114954
    Scenario: Verify on SignUp user should see name, email or phone number in My Account screen
    Given I launch Curbside application for the first time
    And I am on Home Screen
    And I have selected test environment
    And I tap on 'My Account' icon in bottom menu
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    Then I should see my signup information under Account Info
    And I tap on 'Sign Out' button

  @C114958
  Scenario Outline: Add credit card
    Given I launch Curbside application
    And I have selected test environment
    And I signin in using signup information
    And I tap on 'Payment Info' text
    And I tap on 'Add New Card' button
    And I add credit card information as '<first_name>', '<last_name>', '<card_number>', '<card_expiry>', '<card_cvv>', '<card_address1>', '<card_address2>', '<card_city>', '<card_state>', '<card_zip>'
    Then I should see credit info on payment info screen
    And My cart is empty
    And I add any product to cart in 'Palo Alto' location
    When I go to Cart screen
    Then I should see credit info on cart screen

    Examples:
      | first_name | last_name | card_number      | card_expiry | card_cvv |card_address1 | card_address2 | card_city | card_state | card_zip |
      | Test       | Data      | 6011111111111117 | 1120        | 345      |   TestData   |    Test data  |   Boston  |            |   56635  |

  @C114959
  Scenario: Add loyalty card
    Given I launch Curbside application
    And I have selected test environment
    And I signin in using signup information
    And I tap on 'Loyalty Cards' text
    And I tap on 'Add New Card' button
    And I add an ExtraCare Card numbered '87676478652876'
    Then I should see ExtraCare Card info on Loyalty Cards screen
    And My cart is empty
    And I add any product to cart in 'Palo Alto Transit Center' location
    When I go to Cart screen
    Then I should see loyalty card info on cart screen

