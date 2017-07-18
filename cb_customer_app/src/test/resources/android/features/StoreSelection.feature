@StoreSelection
Feature: Android- Store Selection

  Scenario: Setting test environment
    Given I launch Curbside application
    And I am currently in 'Palo Alto, CA' city
    And I have selected test environment

  @Android @C114940
  Scenario: Verify nearby stores display partners/retailers
    Given I am on Home Screen
    And I search for 'Palo Alto Transit Center' location
    Then I should see nearby stores to current location

  @Android @C114999
  Scenario: Verify store lead time
    Given I am on Home Screen
    And I search for 'Boston' location
    When I tap on retailer on Near by stores screen
    Then I should see the lead time below the store address on Store detail page

  @Android @C114939
  Scenario: Verify store location is displayed in map
    Given I am on Home Screen
    And I search for 'Boston' location
    When I tap Map on footer
    Then I should see stores on map for 'Boston' location