@loginValidation
Feature: iOS- Error Message

  @iOS
  Scenario: First time Curbside Launch
    Given I launch CAP application for the first time
    And I accept CAP notifications alerts

  @iOS @TCS01
  Scenario Outline:
    Given I enter "<account>", "<userName>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see this "<errorMessage>" on the screen
    Examples:
      | account  | userName | password | errorMessage                  |
      | curbside |          | curbside | Please enter a valid username |
