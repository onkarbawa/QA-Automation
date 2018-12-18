@proxyChange
Feature: iOS- Proxy Change

  @iOS
  Scenario: First time Curbside Launch (fresh install)
#    Given I launch Setting application
#    And I add manual proxy setting

    Given I launch Safari application
    And  I enter http://www.tftus.com/ URL
    And I search Think Future Technologies
    And I add manual proxy setting
