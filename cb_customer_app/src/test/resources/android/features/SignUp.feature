@signUp
Feature: Android- Signup Flow

  Scenario: Setting test environment
    Given I launch Curbside application
    And I am currently in 'Palo Alto, CA' city
    And I have selected test environment
    
  @Android @C114954
  Scenario: Verify on new account user should see name, email or phone number in my account
    Given I am not signed into application
    And I tap on 'Account' button
    And I tap on 'Sign Up' button
    And I tap on 'Sign Up with Email' button
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    Then I should see my given information under Account Info


  @Android @C114958
  Scenario Outline: Verify user is able to add credit card details from Account section
    Given I am not signed into application
    And I tap on 'Account' button
    And I tap on 'Sign Up' button
    And I tap on 'Sign Up with Email' button
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    And I should see my given information under Account Info
    And I tap on PaymentInfo button on Account page
    And I tap on Add New Card button on Payment info page
    And I add credit card information as '<card_number>', '<card_expiry>', '<ccv>', '<first_name>', '<last_name>', '<card_street_address>', '<apt_suite>','<card_city>', '<card_state>', '<card_zip>'
    Then I should see the card added to Payment info
    Examples:
      | card_number       | card_expiry | ccv | first_name | last_name |card_street_address | apt_suite | card_city | card_state | card_zip |
      | 4012000077777777  | 1218        | 123 | John       | miller    |1 Infinite Loop     |           | Cupertino | California |   95014  |



  @C114959
  Scenario: Add loyalty card
    Given I am not signed into application
    And I tap on 'Account' button
    And I signin in using signup information
    And I tap on 'Loyalty Cards' button
    And I tap on 'Add New Card' button
    And I add an ExtraCare Card numbered '87676478652876'
    Then I should see ExtraCare Card info on Loyalty Cards screen
    And I add any product to cart in 'Palo Alto Transit Center' location
    When I tap on 'Cart' button
    Then I should see loyalty card info on cart screen