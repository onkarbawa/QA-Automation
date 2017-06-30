@applicationLaunch
Feature: Curbside tutorial is prompted when app is launched first time and verify tutorial flow

  @C114937
  Scenario: Tapping 'Get Started' button should take you to Store Selection Page
    Given I launch the Curbside App
    And I tap on 'Skip Intro' button on the screen
    And I open the setting app
