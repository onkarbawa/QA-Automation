@StoreSelection
Feature: Verify store display functionality

  @C114940
  Scenario: Verify nearby stores display partners/retailers
    Given I launch Curbside application for the first time
    And I wait for 'Skip Intro' button
    And I am on Home Screen
    And I search for 'Palo Alto Transit Center' location
    Then I should see nearby stores to current location