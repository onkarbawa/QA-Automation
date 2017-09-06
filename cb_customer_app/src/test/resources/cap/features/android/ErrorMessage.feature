@loginValidation
Feature: Android- Error Message

  @Android
  Scenario: Setting test environment for CAP
    Given I launch Cap application for the first time
    And I accept location access alert

  @Android @TCS01
  Scenario Outline:
    Given I enter "<account>", "<username>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see this "<errorMessage>" on the screen
    Examples:
      | account  | username | password | errorMessage                  |
      | curbside |          | curbside | Please enter a valid username |

