@taskManagement
Feature: iOS | CAP-CVS | SE | Task Management

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

  @iOS @TCS10 @TCS13
  Scenario: Order placed to check - Item ready for pickup
    And I select 'CVS' retailer
    And I select 1no product and save product price named as'readyOrder'
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'readyPickUpOrder'

  @iOS @TCS11
  Scenario: Order placed to check - Item not available (2line items with 1Qty)
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
  Scenario Outline: Setting test environment for CAP-CVS
    Given I launch CAP-CVS application for the first time
    And I have selected test environment for cap
    And I am not signed into application cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @iOS @TCS10 @TCS13 @TCS39
  Scenario: Process standard order (1 item, 1 quantities)
    Given I tap on 'All' tab and search for 'readyPickUpOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'readyPickUpOrder' OrderID and 'confirm' it
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter POS Total'
    And I enter receipt stored price for product 'readyOrder'
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
#    And I tap on 'Pickups' icon in bottom menu for cap
#    And I search by customer name to sort the orders
    Then I should see 'readyPickUpOrder' orderId in PickUp tab with message 'Ready for pickup'
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'
    When I search by customer name to sort the orders
    Then I confirm 'readyPickUpOrder' orderID is not present under Pickups tab

  @iOS @TCS11
  Scenario: Mark all items out of stock (2 items, 1 quantity each)
    Given I tap on 'Close' button
    And I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab and search for 'outOfStock' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'outOfStock' OrderID and 'confirm' it
    And I mark all items as 'Item not Available'
    Then I should see 'outOfStock' orderId in Cancelled pickups with message 'Pickup is Cancelled'