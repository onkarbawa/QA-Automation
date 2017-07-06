@appLaunch
Feature: Verify application functionality on SignUp screen

  @C114958
  Scenario Outline: Add credit card
    Given I launch Curbside application
    And I am signed in using signup information
    And I tap on 'Payment Info' text
    And I tap on 'Add a new card'
    And I add credit card information as <first_name>, <last_name>, <card_number>, <card_expiry>, <card_cvv>, <card_address1>, <card_address2>, <card_city>, <card_state>, <card_zip>
    And I should see credit info on payment info screen
    And I should see credit info on cart screen

    Examples:
      | first_name | last_name | card_number | card_expiry | card_cvv | card_address1 | card_address2 | card_city | card_state | card_zip |
      | Anil       | Kumar     | 7676476746258749 | 05/20   | 345      |		22		|		22		|		22	|	22		 |		22	|


  @C114954
  Scenario: Verify on SignUp user should see name, email or phone number in My Account screen
  Given I launch Curbside application for the first time
  And I am on Home Screen
  And I have selected test environment
  And I tap on 'My Account' icon in bottom menu
  And I tap on 'Create one now' button
  And I tap on 'Create An Account' button
  And I signup for a new account
  And I tap on 'Create Account' button
  Then I should see my signup information under My Account


  @C114959
  Scenario: Add loyalty card
    Given I launch Curbside application
    And I am signed in using signup information
    And I tap on 'Loyalty Cards' text
    And I add an ExtraCare Card numbered '87676478652876'
    And I should see ExtraCare Card info on Loyalty Cards screen
    And I should see loyalty card info on cart screen