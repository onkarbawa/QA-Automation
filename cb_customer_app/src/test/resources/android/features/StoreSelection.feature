@StoreSelection
Feature: Android- Store Selection

  @C114940
  Scenario: Verify nearby stores display partners/retailers
    Given I launch Curbside application for the first time
    And I am on Home Screen
    And I search for 'Palo Alto Transit Center' location
    Then I should see nearby stores to current location

  @C114999
  Scenario: Verify store lead time
    Given I launch Curbside application
    And I wait for application to be launched
    And I search for 'Boston' location
    When I tap on retailer on Near by stores screen
    Then I should see the lead time below the store address on Store detail page

  @C114939
  Scenario: Verify store location is displayed in map
    Given I launch Curbside application
    And I wait for application to be launched
    And I search for 'Boston' location
    When I tap Map on footer
    Then I should see stores on map for 'Boston' location