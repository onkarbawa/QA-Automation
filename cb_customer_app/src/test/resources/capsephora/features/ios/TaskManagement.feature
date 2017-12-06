@taskManagement
Feature: IOS | Task Management

  @iOS
  Scenario: Setting up user account with credit card
    Given I launch Curbside application for the first time
    And I have selected Experimental test environment
    And I am not signed into application
    And I am on 'Walnut Creek' location 'Stores' Screen
    And I tap on 'My Account' icon in bottom menu
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    And I add credit card information

  @iOS @TCS13
  Scenario:  Order placed to check - Claim button functionality
    And I select 'Sephora' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'claimOrder'

  @iOS @TCS14
  Scenario: Order placed to check - Item ready for pickup
    And I go to the shop screen to add any product
    And I select 'Sephora' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'standardOrder'

  @iOS @TCS15
  Scenario: Order placed to check - Item ready for pickup
    And I go to the shop screen to add any product
    And I select 'Sephora' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I select 2no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'outOfStock'

  @iOS @TCS20
  Scenario: Order placed to check - Insufficient Quantity
    And I select 'Sephora' retailer
    And I select 2no product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'insufficientQuantity'

  @iOS @TCS22
  Scenario: Order placed to check - Higher price update
    And I go to the shop screen to add any product
    And I select 'Sephora' retailer
    And I select 1no product from list
    And I store the product price and name it 'actualPrice1'
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'priceUpdate1'

  @iOS @TCS22
  Scenario: Order placed to check - Lower price update
    And I go to the shop screen to add any product
    And I select 'Sephora' retailer
    And I select 1no product from list
    And I store the product price and name it 'actualPrice2'
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'priceUpdate2'

  @iOS
  Scenario Outline: Setting test environment for CAP
    Given I launch CAP Sephora application
    And I have selected test environment for CAP Sephora
    And I am not signed into application cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @iOS @TCS13
  Scenario: Validating Claim button functionality
    Given I tap on 'All' tab and search for 'claimOrder' OrderID and 'claim' it
    Then I look for 'claimOrder' OrderId with 'Unclaim' button
    When I tap on 'Mine' tab
    Then I look for 'claimOrder' OrderId with 'Unclaim' button

  @iOS @TCS14 @TCS19
  Scenario: Process standard order (1 item, 1 quantities)
    Given I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab and search for 'standardOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'standardOrder' OrderID and 'tap' it
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search by customer name to sort the orders
    And I search for 'standardOrder' OrderID
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I sign the customer's signature
    And I tap on 'Done Signing' button
    When I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'
    When I search by customer name to sort the orders
    Then I confirm 'readyPickUpOrder' orderID is not present under Pickups tab

  @iOS @TCS15
  Scenario: Mark all items out of stock (2 items, 1 quantity each)
    Given I tap on 'Close' button if displayed
    And I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab and search for 'outOfStock' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'outOfStock' OrderID and 'confirm' it
    And I mark all items as 'Item not Available'
    Then I should see 'outOfStock' orderId in Cancelled pickups with message 'Pickup is Cancelled'

  @iOS @TCS20
  Scenario: Insufficient Quantity - item Quantity not available (1 item, 2 quantities)
    Given I tap on 'All' tab and search for 'insufficientQuantity' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'insufficientQuantity' OrderID and 'confirm' it
    And I tap on 'Issue' button
    And I enter insufficient quantity '1'
    And I 'Go To Payment' screen
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
#    And I tap on 'Pickups' icon in bottom menu for cap
#    And I search by customer name to sort the orders
    And I should see 'insufficientQuantity' orderId in PickUp tab with message 'Customer Action Needed'
    Then I should see total Order quantity '2' and updated order quantity '1'

  @iOS @TCS22
  Scenario: Change line item pricing - updated price is higher
    Given I tap on 'Close' button if displayed
    And I tap on 'All' tab and search for 'priceUpdate1' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'priceUpdate1' OrderID and 'confirm' it
    And I tap on 'Issue' button
    And I update the latest 'higher' amount of the item as compare to original named as 'actualPrice1'
    And I tap on 'Done' button
    And I 'Go To Payment' screen
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
#    And I tap on 'Pickups' icon in bottom menu for cap
#    And I search by customer name to sort the orders
    And I should see 'priceUpdate1' orderId in PickUp tab with message 'Needs customer attention'

  @iOS @TCS22
  Scenario: Change line item pricing - updated price is lower
    Given I tap on 'Close' button if displayed
    And I tap on 'All' tab and search for 'priceUpdate2' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'priceUpdate2' OrderID and 'confirm' it
    And I tap on 'Issue' button
    And I update the latest 'lower' amount of the item as compare to original named as 'actualPrice2'
    And I tap on 'Done' button
    And I 'Go To Payment' screen
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search by customer name to sort the orders
    And I should see 'priceUpdate2' orderId in PickUp tab with message 'Ready for pickup'