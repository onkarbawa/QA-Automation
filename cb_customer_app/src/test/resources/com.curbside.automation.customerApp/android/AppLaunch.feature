@applicationLaunch
Feature: Curbside tutorial is prompted when app is launched first time and verify tutorial flow

  @C114937
  Scenario: Tapping 'Get Started' button should take you to Store Selection Page
    Given I launch the Android Customer App
    And I scroll left 2 times
    And I click on 'Get Started' button on the Intro page
    And I click on 'Ok with me' button on access page
    When I click on 'Allow Access Location' pop up
    Then I should see the 'Account tab' button
    Then I close the Curbside app

  @C114936
  Scenario: Tapping 'Skip Intro' button should take you to Store Selection Page
    Given I launch the Android Customer App
    And I click on 'Skip Intro' button on the screen
    And I click on 'Ok with me' button on access page
    When I click on 'Allow Access Location' pop up
    Then I should see the 'Account tab' button
    Then I close the Curbside app