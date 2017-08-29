@liveOrder
Feature: Android- Live order

  @Android @C114962
  Scenario Outline: Process first time order end to end
    Given I launch Curbside application for the first time
    And I swipe left 2 times on intro page
    And I tap on 'Get Started' button
    And I tap on 'OK' button on 'location access' page
    When I accept location access alert
    Then I should see 'Nearby stores' landing page
    And I have selected test environment
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    And I tap on Place order button
    And I tap on 'Sign Up' button
    And I tap on 'Sign Up with Email' button
    And I check there is no latest SMS from Curbisde
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    Then I should see my given information under Account Info
    Then I should receive welcome SMS from Curbside app
    And I tap on PaymentInfo button on Account page
    And I tap on Add New Card button on Payment info page
    When I add credit card information as '<card_number>', '<card_expiry>', '<ccv>', '<first_name>', '<last_name>', '<card_street_address>', '<apt_suite>','<card_city>', '<card_state>', '<card_zip>'
    Then I should see the card added to Payment info
    And I tap on 'Account' button
    And I tap on 'Loyalty Cards' button
    And I tap on 'Add New Card' button
    When I add an ExtraCare Card numbered '87676478652876'
    Then I should see ExtraCare Card info on Loyalty Cards screen
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen

  Examples:
  | card_number      | card_expiry | ccv | first_name | last_name | card_street_address | apt_suite | card_city | card_state | card_zip |
  | 4012000077777777 | 1218        | 123 | John       | miller    | 1 Infinite Loop     |           | Cupertino | California | 95014    |
