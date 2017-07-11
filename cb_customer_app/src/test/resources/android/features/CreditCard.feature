@signUp
Feature: Verify Curbside add credit card flow

  #TODO
  @C114958
  Scenario Outline: Verify user is able to add credit card details from Account section
    Given I launch Curbside application for the first time
    And I am on Home Screen
    And I have selected test environment
    And I tap on 'Account' button
    And I tap on 'Sign Up' button
    And I tap on 'Sign Up with Email' button
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    And I should see the Account details with Email id in it
    And I tap on PaymentInfo button on Account page
    And I tap on Add New Card button on Payment info page
    And I add credit card information as '<card_number>', '<card_expiry>', '<ccv>', '<first_name>', '<last_name>', '<card_street_address>', '<apt_suite>','<card_city>', '<card_state>', '<card_zip>'
    Then I should see the card added to Payment info
  Examples:
  | card_number       | card_expiry | ccv | first_name | last_name |card_street_address | apt_suite | card_city | card_state | card_zip |
  | 4012000077777777  | 1218        | 123 | John       | miller    |1 Infinite Loop     |           | Cupertino | California |   95014  |











