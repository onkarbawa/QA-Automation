@loginValidation
Feature: iOS | CAP-CVS | SE | Error Message

  @iOS
  Scenario: First time CapCvs Launch
    Given I launch CAP-CVS application for the first time
    And I have selected test environment for cap
    When I am not signed into application cap

  @iOS @TCS02
  Scenario Outline: Validating valid username
    Given I enter "<account>", "<userName>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see this "<errorMessage>" on the 'Login' screen
    Examples:
      | account  | userName | password | errorMessage                  |
      | curbside |          | curbside | Please enter a valid username |

  @iOS @TCS03
  Scenario Outline: Validating username and password
    Given I enter "<account>", "<username>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see this "<errorMessage>" on the 'Login' screen
    Examples:
      | account | username | password | errorMessage                             |
      | curbside     | qaautomation_initium  | 123456   | Please check username and password and try again. |


