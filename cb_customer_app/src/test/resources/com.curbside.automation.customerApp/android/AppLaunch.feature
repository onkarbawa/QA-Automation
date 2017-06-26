@applicationLaunch
Feature: Check information's and functionality's on Applaunch in Customer app

  @C114936
  Scenario: Verify application is able to launch and we can scroll through intro
    Given I launch the Android Customer App
    And I click three times for Scroll to left
    And I click on 'Get Started' button on the Intro page
    And I click on 'Ok with me' button on access page
    When I click on 'Allow Access Location' pop up
    And I click again if 'Allow Access Location' pop up is visible
    Then I should see the 'Current Location'  button
    And I close the web driver session

  @C114937
  Scenario: Verify application is able to launch and we skip intro
    Given I launch the Android Customer App
    And I click on 'Skip Intro' button on the screen
    And I click on 'Get Started' button on the Intro page
    And I click on 'Ok with me' button on access page
    When I click on 'Allow Access Location' pop up
    When I click on 'Allow Access Location' pop up
    Then I should see the 'Current Location'  button