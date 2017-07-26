@StoreSelection
Feature: iOS- Store Selection

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment

  @iOS @C114940
  Scenario: Verify retailers are shown in store selection screen
    Given I am on Home Screen
    And I am on 'Palo Alto' location 'Stores' Screen
    Then I should see nearby stores

  @iOS @C114939
  Scenario: Verify store location is displayed in map
    And I tap on 'Map' icon in bottom menu
    Then I should see stores on map for 'Palo Alto'
    
  @iOS @C114999
  Scenario: Verify store lead time is displayed
    When I select 'CVS' retailer
    Then I should see lead times to places as below

    
