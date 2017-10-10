@productDetailsAndTaskManagement
Feature: iOS- Product Details and Task Management

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

#    And I Sign-in with 'gilroy_cvs@curbside.com' and 'curbside'
#    And I saw email on MyAccount page
#    And I checked there is any user attention message
#    And My cart is empty
  Scenario: Order placed to check - Product detail & claim order screen
    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
    And I select 'CVS Health Indoor/Outdoor Allergy Relief Tablets' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'claimOrder'

  Scenario: Order placed to check - Item not available (one out of two)
    And I go to the shop screen to add any product
    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
    And I select 'CVS Health Indoor/Outdoor Allergy Relief Tablets' product from list
    And I add 1 quantity of the product
    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
    And I select 'CVS Indoor/Outdoor Allergy Relief Cetirizine Hydrochloride Tablets' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'outOfStock'

  Scenario: Order placed to check - Insufficient Quantity
    And I go to the shop screen to add any product
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

  Scenario: Order placed to check - Cancel pickup functionality
    And I go to the shop screen to add any product
    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
    And I select 'CVS Health Indoor/Outdoor Allergy Relief Tablets' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'cancelPickUpOrder'

  Scenario: Order placed to check - Process Hazmat order (exclamation mark with triangle)
    And I go to the shop screen to add any product
    And I select 'CVS' retailer and search for 'Hairspray Aerosol'
    And I select 'Aussie Mega Aerosol Hairspray' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'hazmatOrder'

  Scenario Outline: Setting test environment for CAP
    Given I launch CAP application for the first time
    And I have selected test environment for cap
    And I am not signed into application cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @iOS @TCS07
  Scenario: Validating Claim button functionality
    Given I search for 'claimOrder' Order ID and claim it
    And I tap on 'Mine' tab
    Then I look for 'claimOrder' Order Id under 'Mine' tab

  @iOS @TCS06
  Scenario: Verify that on Product Details screen
    And I search for 'claimOrder' OrderID
    Then I should see product details as below for CAP

  @iOS @TCS08
  Scenario: Mark an item out of stock
    And I tap on back button
    And I tap on 'Issue' button
    And I turn 'ON' 'Item not Available'
    And I tap on 'Done' button
    And I tap on 'Finish' button
    And I ckecked order is ready
    Then I should see 'claimOrder' orderId in PickUp tab with 'Needs customer attention'


  @iOS @TCS09
  Scenario: Mark all items out of stock (2 items, 1 quantity each)
    Given I tap on 'Close' button
    And I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab
    And I search for 'outOfStock' Order ID and claim it
    And I tap on 'Mine' tab
    And I search for 'outOfStock' OrderID
    And I mark all items as 'Item not Available'
    Then I should see 'outOfStock' orderId in Cancelled pickups with message 'Pickup is Cancelled'

  @iOS @TCS11
  Scenario: Mark item Quantity not available (1 item, 2 quantities)
    Given I tap on 'Close' button
    And I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab
    And I search for 'insufficientQuantity' Order ID and claim it
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

  @iOS @TCS13
  Scenario: Verify cancelled pickup order (1 item, 1 quantities)
    Given I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab
    And I search for 'cancelPickUpOrder' Order ID and claim it
    And I tap on 'Mine' tab
    And I search for 'cancelPickUpOrder' OrderID
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I ckecked order is ready
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search for 'cancelPickUpOrder' OrderID
    And I tap on 'Cancel This Pickup' button
    And I tap on 'Cancel Pickup' button
    And I tap on 'Cancel - Customer No Show' button
    Then I should see 'cancelPickUpOrder' orderId in Tasks screen under 'Cancelled Pickup'

  @iOS @TCS24
  Scenario: Verify cancelled pickup order (1 item, 1 quantities)
    Given I tap on 'Close' button
    And I search for 'hazmatOrder' Order ID and verify that 'Hazmat Symbol' is present
    And I tap on 'Mine' tab
    And I search for 'hazmatOrder' OrderID
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I ckecked order is ready
    Then I tap on 'Pickups' icon and search for 'hazmatOrder' OrderID and verify that 'Hazmat Symbol' is present
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'




