@StoreSelection
Feature: Verify store display functionality

  @C114940
  Scenario: Verify store location is displayed in map
    Given I launch Curbside application for the first time
    And I have selected test environment
    And I am on 'Stores' Screen
    Then I should see nearby stores to current location
    
  @C114939 @C114999
  Scenario: Verify store location is displayed in map
    Given I am on 'Stores' Screen
    And I select 'Metro Areas' > 'Boston' location
    And I tap on 'Map' icon in bottom menu
    Then I should see stores on map for 'Boston'
    When I select 'Newton' retailer partner on Stores screen
    Then I should see lead times to places as below
    	| place 	| lead_time	|
      	| CVS   	| 1 hr		|
      	| Pizza Hut | 15 min	|
      	| Sephora 	| 3 hrs		|