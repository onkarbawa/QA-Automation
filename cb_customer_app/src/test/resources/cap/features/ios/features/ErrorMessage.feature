@loginValidation
Feature: iOS- CAP ErrorMessage

  @iOS
  Scenario: First time Cap Launch
    Given I launch CAP application for the first time
    And I have selected test environment for cap
    When I am not signed into application cap

  @iOS @TCS01
  Scenario Outline: Validating valid username
    Given I enter "<account>", "<userName>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see this "<errorMessage>" on the 'Login' screen
    Examples:
      | account  | userName | password | errorMessage                  |
      | curbside |          | curbside | Please enter a valid username |

  @iOS @TCS02
  Scenario Outline: Validating password should be at least 7 characters
    Given I enter "<account>", "<username>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see this "<errorMessage>" on the 'Login' screen
    Examples:
      | account | username | password | errorMessage                             |
      | cvs     | 1234567  | 123456   | Password should be at least 7 characters |

  @iOS @TCS03
  Scenario Outline: Validating EmpId should be of 7 digits
    Given I enter "<account>", "<username>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see this "<errorMessage>" on the 'Login' screen
    Examples:
      | account | username | password | errorMessage                             |
      | cvs     | 123456  | 1234567   | Please enter 7-digit EMP ID ex. 0001234 |

