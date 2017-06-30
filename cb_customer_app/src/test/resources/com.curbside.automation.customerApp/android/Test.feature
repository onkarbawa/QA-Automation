@applicationLaunch
Feature: Curbside tutorial is prompted when app is launched first time and verify tutorial flow

  #This is for stock android - Working on Moto g2
  @C114998
  Scenario: Tapping 'Get Started' button should take you to Store Selection Page
    Given I launch the Curbside App
    And I open the setting app
    And I search and tap on'App permissions' on the screen
    And I tap on 'App permissions' button
    And I tap on 'Location' button on the screen
    Then I look for Curbside app and it should shows only gray/white


