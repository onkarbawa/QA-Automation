@loginValidation
Feature: IOS | CAP Sephora | Error Message

  @iOS
  Scenario: First time Cap Launch
    Given I launch CAP Sephora application
    And I have selected test environment for CAP Sephora
    When I am not signed into application cap


  @iOS @TCS10
  Scenario Outline: Validating valid username
    Given I enter "<account>", "<userName>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see this "<errorMessage>" on the 'Login' screen
    Examples:
      | account  | userName | password | errorMessage                  |
      | curbside |          | 1234567  | Please enter a valid username |

  @iOS @TCS11
  Scenario Outline: Validating password should be at least 7 characters
    Given I enter "<account>", "<userName>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see this "<errorMessage>" on the 'Login' screen
    Examples:
      | account  | userName | password | errorMessage                  |
      | curbside | curbside |          | Please enter a valid password |

  @iOS @TCS12
  Scenario Outline: Validating password should be at least 7 characters
    Given I enter "<account>", "<userName>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see this "<errorMessage>" on the 'Login' screen
    Examples:
      | account  | userName             | password | errorMessage                                      |
      | curbside | qaautomation_initium | 123456   | Please check username and password and try again. |
