@StoreSelection
Feature: iOS- Store Selection

  @C114940
  Scenario: Verify retailers are shown in store selection screen
    Given I launch Curbside application for the first time
    And I am on Home Screen
    And I am on 'Palo Alto' location 'Stores' Screen
    Then I should see nearby stores

  @C114939
  Scenario: Verify store location is displayed in map
    Given I launch Curbside application
    And I am on Home Screen
    And I am on 'Palo Alto' location 'Stores' Screen
    And I tap on 'Map' icon in bottom menu
    Then I should see stores on map for 'Palo Alto'
    
  @C114999
  Scenario: Verify store lead time is displayed
    Given I launch Curbside application
    And I am on Home Screen
    And I am on 'Palo Alto' location 'Stores' Screen
    When I select a store
    Then I should see lead times to places as below

    
