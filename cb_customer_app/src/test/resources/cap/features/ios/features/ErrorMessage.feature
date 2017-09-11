@loginValidation
Feature: iOS- Error Message

  @iOS
  Scenario: First time Curbside Launch
    Given I launch CAP application for the first time
    And I accept CAP notifications alerts

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
      | cvs     | 1234567  | 123456   | Password should be at least 7 characters |

  @iOS @TCS04
  Scenario Outline: Validating EmpId should be of 7 digits
    Given I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    And I should see 'Task' screen
    And I turn 'OFF' 'Airplane Mode' through Control Center
    And I accept CAP notifications alerts
    Then I should see this "<errorMessage>" on the 'Tasks' screen
    Examples:
      | account | username | password | errorMessage                            |
      | curbside     | qaautomation_initium   | curbside | Offline - Not all features are available |