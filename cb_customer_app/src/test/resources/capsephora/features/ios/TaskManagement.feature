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
  Scenario: Mark item Quantity not available (1 item, 2 quantities)
    Given I tap on 'All' tab and search for 'claimOrder' OrderID and 'claim' it
    Then I look for 'claimOrder' OrderId with 'Unclaim' button
    When I tap on 'Mine' tab
    Then I look for 'claimOrder' OrderId with 'Unclaim' button

  @iOS @TCS14
  Scenario: Process standard order (1 item, 1 quantities)
    Given I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab and search for 'standardOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'standardOrder' OrderID and 'tap' it
    And I tap on 'Got It' button
#    And I scan Barcodes and tap on 'Show Barcodes' button   // Mail sent to Seejo to confirm this step
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search by customer name to sort the orders
    And I search for 'claimOrder' OrderID
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I sign the customer's signature
    And I tap on 'Done Signing' button
    When I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'

  @iOS @TCS15
  Scenario: Mark all items out of stock (2 items, 1 quantity each)
    Given I tap on 'Close' button if displayed
    And I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab and search for 'outOfStock' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'outOfStock' OrderID and 'confirm' it
    And I mark all items as 'Item not Available'
    Then I should see 'outOfStock' orderId in Cancelled pickups with message 'Pickup is Cancelled'
