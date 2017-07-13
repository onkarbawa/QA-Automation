@StoreSelection
Feature: Verify store display functionality

  @C114940
  Scenario: Verify store location is displayed in map
    Given I launch Curbside application for the first time
    And I am on Home Screen
    And I have selected test environment
    And I search for 'Palo Alto Transit Center' location
    Then I should see nearby stores to current location

  @C114999
  Scenario: Verify store lead time
    Given I launch Curbside application for the first time
    And I am on Home Screen
    And I have selected test environment
    And I search for 'Boston' location
    When I tap on retailer on Near by stores screen
    Then I should see the lead time below the store address on Store detail page

  @C114939
  Scenario: Verify store location is displayed in map
    Given I launch Curbside application for the first time
    And I am on Home Screen
    And I have selected test environment
    And I search for 'Boston' location
    When I tap Map on footer
    Then I should see stores on map for 'Boston' location