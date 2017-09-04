@applicationLaunch
Feature: Android- Cap Launch

  Curbside tutorial is prompted when app is launched first time and verify tutorial flow

  @Android @C114937
  Scenario Outline: Tapping 'Get Started' button should take you to Store Selection Page
    Given I launch Cap application for the first time
    And I accept location access alert
    And I have selected test environment for CAP
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button on intro page
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |
