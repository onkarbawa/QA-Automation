@creditAndLoyaltyCards
Feature: Android- Account cards verification

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment

  Scenario: SignUp for the first time
    Given I am not signed into application
    And I tap on 'Account' button
    And I tap on 'Sign Up' button
    And I tap on 'Sign Up with Email' button
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    Then I should see my given information under Account Info

  @Android @C114960
  Scenario Outline: User is able to ADD the Credit card
    Given I tap on PaymentInfo button on Account page
    And I tap on Add New Card button on Payment info page
    And I add credit card information as '<card_number>', '<card_expiry>', '<ccv>', '<first_name>', '<last_name>', '<card_street_address>', '<apt_suite>','<card_city>', '<card_state>', '<card_zip>'
    Then I should see the card added to Payment info
    Examples:
      | card_number      | card_expiry | ccv | first_name | last_name | card_street_address | apt_suite | card_city | card_state | card_zip |
      | 4012000077777777 | 1218        | 123 | John       | miller    | 1 Infinite Loop     |           | Cupertino | California | 95014    |


  @Android @C114961
  Scenario: User is able to ADD loyalty card
    Given I tap on 'Account' button
    And I tap on 'Loyalty Cards' button
    And I tap on 'Add New Card' button
    When I add an ExtraCare Card numbered '87676478652876'
    Then I should see ExtraCare Card info on Loyalty Cards screen

  @Android @C114960 @C114961
  Scenario: User is able to see Credit and Loyalty card info under Cart screen
    Given I add any product to cart in 'Palo Alto' location
    When I tap on 'Cart' button
    Then I should see credit card info on cart screen
    Then I should see loyalty card info on cart screen

  @Android @C114960
  Scenario: User is able to REMOVE the Credit card
    Given I tap on 'Account' button
    And I tap on 'Payment Info' button
    When I tap on 'REMOVE' button
    Then I should see empty Payment Info screen

  @Android @C114961
  Scenario: User is able to REMOVE loyalty card
    Given I tap on 'Account' button
    And I tap on 'Loyalty Cards' button
    When I tap on 'REMOVE' button
    Then I should see empty Loyalty Cards screen