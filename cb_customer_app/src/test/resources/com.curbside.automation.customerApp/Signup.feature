@signUp
Feature: Check information's and functionality's on SignUp in Customer app

  @C114954
  Scenario: Verify on new account user should see name, email or phone number in my account
    Given I launch the Customer App
    And I click on 'My Account' icon
    And I click on 'Create an Account'
    And I enter 'email' in first box
    And I enter 'password' in second box
    And I enter 'Phone Number' in third box
    When I click on 'Create Account' button
    Then I should see name, email or phone number in my account

  @C114958
  Scenario: Verify Credit card info is shown in 'Payment Info' in 'My Account'
    Given I launched the application of Customer
    And I click on 'My Account' icon
    And I click on 'Create an Account'
    And I enter 'email' in first box
    And I enter 'password' in second box
    And I enter 'Phone Number' in third box
    When I click on 'Create Account' button

  @incomplete
  @C114959