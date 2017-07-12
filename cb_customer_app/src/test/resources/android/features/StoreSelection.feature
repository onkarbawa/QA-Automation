@StoreSelection
Feature: Verify store display functionality

  @C114940
  Scenario: Verify store location is displayed in map
    Given I launch Curbside application for the first time
    And I am on Home Screen
    And I search for 'Palo Alto Transit Center' location
    Then I should see nearby stores to current location