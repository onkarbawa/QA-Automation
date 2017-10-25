@taskManagement1
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

  Scenario: Order placed to check - Insufficient Quantity
    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
    And I select 'CVS Health Indoor/Outdoor Allergy Relief Tablets' product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'insufficientQuantity'

  Scenario: Order placed to check - Item ready for pickup
    And I go to the shop screen to add any product
    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
    And I select 'CVS Health Indoor/Outdoor Allergy Relief Tablets' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'readyPickUpOrder'

  Scenario Outline: Setting test environment for CAP
    Given I launch CAP application for the first time
    And I have selected test environment for cap
    And I am not signed into application cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @iOS @TCS11
  Scenario: Mark item Quantity not available (1 item, 2 quantities)
    Given I search for 'insufficientQuantity' Order ID and claim it
    And I tap on 'Mine' tab
    And I search for 'insufficientQuantity' OrderID
    And I tap on 'Issue' button
    And I enter insufficient quantity '1'
    And I 'Go To Payment' screen
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I ckecked order is ready
    And I tap on 'Pickups' icon in bottom menu for cap
    And I should see 'insufficientQuantity' orderId in PickUp tab with 'Needs customer attention'
    Then I should see total Order quantity '2' and updated order quantity '1'

  @iOS @TCS12 @TCS16
  Scenario: Process standard order (1 item, 1 quantities)
    Given I tap on 'Close' button
    And I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab
    And I search for 'readyPickUpOrder' Order ID and claim it
    And I tap on 'Mine' tab
    And I search for 'readyPickUpOrder' OrderID
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I ckecked order is ready
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search for 'readyPickUpOrder' OrderID
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'
    When I search by customer name to sort the orders
    Then I confirm 'readyPickUpOrder' orderID is not present under Pickups tab