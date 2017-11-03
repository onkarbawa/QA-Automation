@login
Feature: iOS- Login Feature

  Scenario: Setting test environment 
    Given I launch Curbside application for the first time
    And I have selected test environment

#  @iOS @C114957
#  Scenario: Verify New user should login in curbside app
#    Given I am not signed into application
#    And I tap on 'My Account' button
#    And I tap on 'Sign In' button
#    And I tap on 'Create An Account' button
#    And I signup for a new account
#    Then I should see my signup information under My Account

  @iOS @C114956
  Scenario Outline: Verify Old user should login in curbside app
    Given I am not signed into application
    And I tap on 'Sign In' button
    And I tap on 'Sign In with Email' button
    And I enter '<Email>' and '<Password>'
    Then I saw email on MyAccount page
    Examples:
      |             Email                  |     Password    |
      |      fusic.test1@gmail.com         |     fusic@123   |

  @iOS @C114955
  Scenario Outline: Verify Facebook user should login in curbside app
    Given I launch Curbside application
    And I am not signed into application
    And I login to facebook in browser with '<Email>' and '<Password>'
    Then I should be logged into application

    Examples:
      |             Email                  |     Password    |
      |      ustft123@gmail.com            |     Admin2642    |