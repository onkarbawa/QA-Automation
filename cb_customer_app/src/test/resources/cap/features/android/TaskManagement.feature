@applicationLaunch
Feature: Android- Task Management

  Scenario Outline: Scenario: Setting test environment
    Given I launch Cap application for the first time
    And I have selected test environment for CAP
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @Android @C114937
  Scenario: Generate Error Payment Order
    Given I tap on 'Pickups' button
