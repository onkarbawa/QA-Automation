@signUp
Feature: Check informations and functionalitys on SignUp in Customer app

  @C114954
  Scenario: Verify on new account user should see name, email or phone number in my account
    Given I launch the Customer App
    And I clicked on Allow to send notifications
    And I click on 'Skip Intro' button
    And I click on 'Ok with me' button on access landing page
    And I click on 'Allow Access Location' button
    And I click on 'My Account' icon
    And I click on create one now
    And I click on 'Create an Account'
    And I enter 'email' in first box
    And I enter 'password' in second box
    And I enter 'Phone Number' in third box
    When I click on 'Create Account' button
    Then I should see email or phone number in my account
    And I close the web driver session for ios

   ~@C114958
  Scenario: Verify Credit card info is shown in 'Payment Info' in 'My Account'
    Given I launch the Customer App
    And I clicked on Allow to send notifications
    And I click on 'Skip Intro' button
    And I click on 'Ok with me' button on access landing page
    And I click on 'Allow Access Location' button
    And I click on 'My Account' icon
    And I click on 'Create an Account'
    And I enter 'email' in first box
    And I enter 'password' in second box
    And I enter 'Phone Number' in third box
    And I click on 'Create Account' button


