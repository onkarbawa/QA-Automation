@OfflineValidation
Feature: iOS- Offline Error Message

  @iOS
  Scenario: First time Curbside Launch
    Given I launch CAP application
    And I have selected test environment for cap

  @iOS @TCS04
  Scenario Outline: Verifying Offline error message on Airplane Mode
  Given I enter "<account>", "<username>" and "<password>" for login
  And I tap on 'Login' button
  And I should see 'Tasks' screen
  And I turn 'OFF' 'Airplane Mode' through Control Center
  And I accept CAP notifications alerts
  Then I should see this "<errorMessage>" on the 'Tasks' screen
  Examples:
  | account | username | password | errorMessage                            |
  | curbside     | qaautomation_initium   | curbside | Offline - Not all features are available |