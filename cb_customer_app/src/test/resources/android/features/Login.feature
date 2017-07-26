@login
Feature: Android- Login Feature

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment
    
  @Android @C114957
  Scenario: Verify New user should login in curbside app
    Given I am not signed into application
    And I tap on 'Account' button
    And I tap on Sign up button on My Account page
    And I tap on 'Sign Up with Email' button
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    Then I should see my given information under Account Info

  #TODO: yet to get update on Facebook login from Seejo
  @Android @C114955
  Scenario Outline: Verify user should login with facebook
    Given I am not signed into application
    And I tap on 'Account' button
    And I tap on 'Sign In' button
    And I tap on 'Sign in with Facebook' button
    And I enter '<facebook_email>' and '<facebook_password>' for facebook login
    Then I should see email as '<facebook_email>' on Account Info page
    And I should see name as '<facebook_name>' on Account Info page
    And I should see phone number as '<facebook_phone>' on Account Info page
  Examples:
    | facebook_email		|	facebook_password	| facebook_name	| facebook_phone	|
    | ustft123@gmail.com   	|	Admin2642    		| Tfttest Gigya	| 1 (134)-567-8901	|

  @Android @C114956
  Scenario Outline: Verify user should login with email
    Given I am not signed into application
    And I tap on 'Account' button
    And I tap on 'Sign In' button
    And I tap on 'Sign In with Email' button
    And I enter "<email>" and "<password>" for login
    When I tap on sign in button
    Then I should be logged in to the application
  Examples:
    |       email         |      password     |
    |fusic.test1@gmail.com|fusic@123          |