@applicationLaunch
Feature: Android- Application Launch

  Scenario: Setting up the environment
    Given I launch NAMSHI application for the first time
    And I saw country selection screen
    And I tap on 'Saudi Arabia' button
    And I tap on 'Start' button
    Then I saw gender selection screen
    And I tap on 'SHOP Women' button