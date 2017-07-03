@applicationLaunch
Feature: Curbside tutorial is prompted when app is launched first time and verify tutorial flow

  @C114937
  Scenario: Tapping 'Get Started' button should take you to Store Selection Page
    Given I launch Curbside application
    And I scroll left 3 times
    And I tap on Get Started button
    And I tap on 'Ok with me' button on Location access screen
    When I tap on 'Allow Access Location' pop up
    Then I should see the Nearby Stores screen

  @C114936
  Scenario: Tapping 'Skip Intro' button should take you to Store Selection Page
    Given I reinstall the Curbside App
    And I tap on 'Skip Intro' button on the screen
    And I tap on 'Ok with me' button on Location access screen
    When I tap on 'Allow Access Location' pop up
    Then I should see the Nearby Stores screen