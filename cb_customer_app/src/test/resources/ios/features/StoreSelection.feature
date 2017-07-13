@StoreSelection
Feature: Verify store display functionality

  @C114940
  Scenario: Verify retailers are shown in store selection screen
    Given I launch Curbside application for the first time
    And I am on Home Screen
    And I have selected test environment
    And I am on 'Palo Alto' location 'Stores' Screen
    Then I should see nearby stores

  @C114939
  Scenario: Verify store location is displayed in map
    Given I launch Curbside application
    And I am on 'Palo Alto' location 'Stores' Screen
    And I tap on 'Map' icon in bottom menu
    Then I should see stores on map for 'Palo Alto'
    
  @C114999
  Scenario: Verify store lead time is displayed
    Given I launch Curbside application
    And I am on 'Palo Alto' location 'Stores' Screen
    Then I should see lead times to places as below
    	| place 	| lead_time	|
      	| CVS   	| 1 hr		|
      	| Pizza Hut | 15 min	|
      	| Sephora 	| 3 hrs		|
    
