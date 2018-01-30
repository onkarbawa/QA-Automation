@myAccount
Feature: iOS- Account flow Cards verification

  Scenario: Setting test environment
    Given I launch Curbside application for the first time
    And I have selected Experimental test environment
#    And I am not signed into application
#    And I am on 'Palo Alto' location 'Stores' Screen


  @iOS @C114956
  Scenario Outline: Verify Old user should login in curbside app
    Given I am not signed into application
    And I tap on 'Sign In' button
    And I tap on 'Sign In with Email' button
    And I enter '<Email>' and '<Password>'
    Then I saw email on MyAccount page
    Examples:
      | Email             | Password   |
      | test123@gmail.com | 1234567890 |

  @iOS @C114961
  Scenario: Verify that Loyality Card Add/Remove
    Then I removed added 'Loyality Card' from 'Loyality Card' Screen