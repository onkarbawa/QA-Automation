@applicationLaunch
Feature: Check information's and functionality's on Applaunch in Customer app

  @C114936
  Scenario: Verify application is able to launch and checkmark is set to 'Always'
    Given I launch the Android Customer App
    And I click three times for Scroll to left
    And I click on 'Get Started' button on the Intro page
    And I click on 'Ok with me' button on access page
    When I click on 'Allow Access Location' pop up
    Then I should see the 'Current Location'  button