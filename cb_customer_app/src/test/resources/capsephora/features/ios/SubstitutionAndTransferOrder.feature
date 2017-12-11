@substitutionAndTransferOrder
Feature: IOS | CAP Sephora | Substitution And Transfer Order

  @iOS
  Scenario: Setting up user account with credit card
    Given I launch Curbside application
    And I have selected Experimental test environment
    And I am not signed into application
    And I am on 'Walnut Creek' location 'Stores' Screen
    And I tap on 'My Account' icon in bottom menu
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    And I add credit card information

  @iOS @TCS016
  Scenario: Order Placed to check - Accept substitution Order
    And I select 'Sephora' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'substitutionOrder'

  @iOS @TCS017
  Scenario: Order placed to check - Partial decline substitution Order
    And I go to the shop screen to add any product
    And I select 'Sephora' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I select 2no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'transferOrder'

  Scenario Outline: Setting test environment for CAP
    Given I launch CAP Sephora application
    And I have selected test environment for CAP Sephora
    And I am not signed into application cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @iOS @TCS016
  Scenario: Substitution Order Accepted- All items out of stock (1 item, 1 quantity each)
    Given I tap on 'All' tab and search for 'substitutionOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'substitutionOrder' OrderID and 'confirm' it
    And I mark all items as 'Item not Available'
    And I should see 'substitutionOrder' orderId in PickUp tab with message 'Needs customer attention'
    And I launch Curbside application
    And I accept the substitution order
    And I launch CAP Sephora application
    And I accept CAP notifications alerts
    And I tap on 'All' tab and search for 'substitutionOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'substitutionOrder' OrderID and 'confirm' it
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search by customer name to sort the orders
    And I search for 'substitutionOrder' OrderID
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I sign the customer's signature
    And I tap on 'Done Signing' button
    When I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'

  @iOS @TCS017
  Scenario: Partial substitution order declined - 1 item out of stock (2 items, 1 quantity each)
    Given I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab and search for 'transferOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'transferOrder' OrderID and 'confirm' it
    And I tap on 1 'Got It' button
    And I tap on 2 'Issue' button
    And I turn 'ON' 'Item not Available'
    And I tap on 'Done' button
    And I 'Go To Payment' screen
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    And I should see 'transferOrder' orderId in PickUp tab with message 'Needs customer attention'
    When I launch Curbside application
    Then I decline the substitution order
    And I launch CAP Sephora application
    And I accept CAP notifications alerts
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search by customer name to sort the orders
    And I search for 'transferOrder' OrderID
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I sign the customer's signature
    And I tap on 'Done Signing' button
    When I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'