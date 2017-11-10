@loginValidation
Feature: Android | CAP EE | Error Message

  @Android
  Scenario: Setting test environment for CAP
    Given I launch Cap application for the first time
    And I accept location access alert

  @Android @TCS01
  Scenario Outline: Validating valid username
    Given I enter "<account>", "<username>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see this "<errorMessage>" on the screen
    Examples:
      | account  | username | password | errorMessage                  |
      | curbside |          | curbside | Please enter a valid username |

  @Android @TCS02
  Scenario Outline: Validating password should be at least 7 characters
    Given I enter "<account>", "<username>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see this "<errorMessage>" on the screen
    Examples:
      | account | username | password | errorMessage                             |
      | cvs     | 1234567  | 123456   | Password should be at least 7 characters |

  @Android @TCS03
  Scenario Outline: Validating EmpId should be of 7 digits
    Given I enter "<account>", "<username>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see this "<errorMessage>" on the screen
    Examples:
      | account | username | password | errorMessage                            |
      | cvs     | 123456   | curbside | Please enter 7-digit EMP ID ex. 0001234 |