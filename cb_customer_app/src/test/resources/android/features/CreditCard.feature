@signUp
Feature: Verify Curbside add credit card flow

  #TODO
  @C114958
  Scenario: Verify user is able to add credit card details from Account section
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










