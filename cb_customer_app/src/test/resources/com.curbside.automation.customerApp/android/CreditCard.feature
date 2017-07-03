@signUp
Feature: Verify Curbside add credit card flow

  @C114958
  Scenario: Verify user is able to add credit card details from Account section
    Given I launch Curbside application
    And I land on store selection screen
    And I set the staging environment
    And I tap on 'Account' icon
    And I tap on 'Sign up' button on Account page
    And I tap on Sign up with email button
    And I enter 'email' in first text box
    And I go to next text field
    And I enter 'Phone Number' in third text box
    And I go to next text field
    And I enter 'password' in second text box
    And I tap on 'Create Account' button on the page
    And  I should see the Account details with Email id in it
    And I tap on PaymentInfo button on Account page
    And I tap on Add New Card button on Payment info page
    And I send '4012000077777777' in 'card number'
    And I send '1218' in 'expired month'
    And I send '123' in 'ccv'
    And I send 'John' in 'first name'
    And I send 'miller' in 'last name'
    And I send '1 Infinite Loop' in 'Street address'
    And I send 'Cupertino' in 'city'
    And I select state from the list
    And I send '95014' in 'Zip code'
    When I tap on Save button on the screen
    Then I should see the card added to Payment info










