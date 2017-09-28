@taskManagement
Feature: iOS- Task Management

  Scenario: Setting up user account with credit card
    Given I launch Curbside application for the first time
    And I have selected Experimental test environment
    And I am not signed into application
    And I am on 'Gilroy' location 'Stores' Screen
    And I tap on 'My Account' icon in bottom menu
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    And I add credit card information

  Scenario: Order placed to check - Item not available (one out of two)
    And I go to the shop screen to add any product
    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
    And I select 'CVS Health Indoor/Outdoor Allergy Relief Tablets' product from list
#    And I select 1no product from 'CVS' store
    And I add 1 quantity of the product
    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
    And I select 'CVS Indoor/Outdoor Allergy Relief Cetirizine Hydrochloride Tablets' product from list
#    And I select 2no product from 'CVS' store
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'outOfStock'

  Scenario: Order placed to check - Item not available (one out of two)
    And I go to the shop screen to add any product
    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
    And I select 'CVS Health Indoor/Outdoor Allergy Relief Tablets' product from list
#    And I select 1no product from 'CVS' store
    And I add 1 quantity of the product
    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
    And I select 'CVS Indoor/Outdoor Allergy Relief Cetirizine Hydrochloride Tablets' product from list
#    And I select 2no product from 'CVS' store
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'outOfStock'

  @iOS @TCS014
  Scenario: Mark all items out of stock (2 items, 1 quantity each)
    Given I tap on 'Close' button
    And I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab
    And I search for 'outOfStock' Order ID and claim it
    And I tap on 'Mine' tab
    And I search for 'outOfStock' OrderID
    And I mark all items as 'Item not Available'
    Then I should see 'outOfStock' orderId in Cancelled pickups with message 'Pickup is Cancelled'