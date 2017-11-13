@productDetailsAndTaskManagement
Feature: iOS- Product Details and Task Management

  Scenario: Check BackGroundRefresh and Allow Notifications is ON
    Given I turn 'ON' 'Background App Refresh' and 'Allow Notifications' for 'Curbside' app

  @iOS
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

  @iOS @TCS06 @TCS07 @TCS08
  Scenario: Order placed to check - Product detail & claim order screen
    And I select 'CVS' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'claimOrder'

  @iOS @TCS09
  Scenario: Order placed to check - Item not available (one out of two)
    And I go to the shop screen to add any product
    And I select 'CVS' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I select 2no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'outOfStock'

  @iOS
  Scenario Outline: Setting test environment for CAP
    Given I launch CAP application
    And I have selected test environment for cap
    And I am not signed into application cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @iOS @TCS07
  Scenario: Validating Claim button functionality
    Given I tap on 'All' tab and search for 'claimOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab
    Then I look for 'claimOrder' Order Id under 'Mine' tab

  @iOS @TCS06
  Scenario: Verify that on Product Details screen
    And I tap on 'Mine' tab and search for 'claimOrder' OrderID and 'confirm' it
    Then I should see product details as below for CAP

  @iOS @TCS08
  Scenario: Mark an item out of stock
    And I tap on back button
    And I tap on 'Issue' button
    And I turn 'ON' 'Item not Available'
    And I tap on 'Done' button
    And I tap on 'Finish' button
    And I checked order is ready
    Then I should see 'claimOrder' orderId in PickUp tab with message 'Needs customer attention'

  @iOS @TCS09
  Scenario: Mark all items out of stock (2 items, 1 quantity each)
    Given I tap on 'Close' button
    And I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab and search for 'outOfStock' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'outOfStock' OrderID and 'confirm' it
    And I mark all items as 'Item not Available'
    Then I should see 'outOfStock' orderId in Cancelled pickups with message 'Pickup is Cancelled'




