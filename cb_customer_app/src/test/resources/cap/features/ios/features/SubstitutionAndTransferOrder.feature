@substitutionAndTransferOrder
Feature: iOS- Task Management1

  @iOS
  Scenario: Setting up user account with credit card
    Given I launch Curbside application
    And I have selected Experimental test environment
    And I am not signed into application
    And I am on 'Gilroy' location 'Stores' Screen
    And I tap on 'My Account' icon in bottom menu
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    And I add credit card information

  @iOS @TCS014
  Scenario: Order Placed to check - Substitution Order
    And I select 'CVS' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'substitutionOrder'

  @iOS @TCS015
  Scenario: Order placed to check - Transfer Order
    And I go to the shop screen to add any product
    And I select 'CVS' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I select 2no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'transferOrder'

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

  @iOS @TCS014
  Scenario: Substitution Order - All order out of stock (1 item, 1 quantity each)
    Given I tap on 'All' tab and search for 'substitutionOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'substitutionOrder' OrderID and 'confirm' it
    And I mark all items as 'Item not Available'
    And I should see 'substitutionOrder' orderId in PickUp tab with 'Needs customer attention'
    And I launch Curbside application
    And I changed the order item
    And I launch CAP application
    And I accept CAP notifications alerts
    And I tap on 'All' tab and search for 'substitutionOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'substitutionOrder' OrderID and 'confirm' it
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search by customer name to sort the orders
    And I search for 'substitutionOrder' OrderID
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'

  @iOS @TCS015
  Scenario: Transfer Order - 1 item out of stock (2 items, 1 quantity each)
    Given I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab and search for 'transferOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'transferOrder' OrderID and 'confirm' it
    And I tap on 1 'Got It' button
    And I tap on 2 'Issue' button
    And I turn 'ON' 'Item not Available'
    And I tap on 'Done' button
    And I 'Go To Payment' screen
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    And I should see 'transferOrder' orderId in PickUp tab with 'Needs customer attention'
    And I launch Curbside application
    And I decline the substitution order
    And I launch CAP application
    And I accept CAP notifications alerts
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search by customer name to sort the orders
    And I search for 'transferOrder' OrderID
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'