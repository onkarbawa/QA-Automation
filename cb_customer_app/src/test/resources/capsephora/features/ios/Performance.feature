@performance
Feature: IOS | Performance

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

  @iOS @TCS35
  Scenario:  Order placed to check - order removed from pickups after cancellation
    And I select 'Sephora' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'cancellationOrder'

  @iOS @TCS36
  Scenario:  Order placed to check - "Ready for pickup" status immediately after making order ready
    And I go to the shop screen to add any product
    And I select 'Sephora' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'readyOrder'

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


  @iOS @TCS35
  Scenario: Order removed from pickups after cancellation (1 item, 1 quantities)
    Given I tap on 'All' tab and search for 'cancellationOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'cancellationOrder' OrderID and 'confirm' it
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search by customer name to sort the orders
    And I should see 'cancellationOrder' orderId in PickUp tab with message 'Ready for pickup'
    When I launch Curbside application
    Then I decline the substitution order
    And I launch CAP Sephora application
    And I accept CAP notifications alerts
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search by customer name to sort the orders
    When I search for 'cancellationOrder' OrderID
    Then I confirm 'cancellationOrder' orderID is not present under Pickups tab


  @iOS @TCS36
  Scenario: Order status should be "Ready for pickup" immediately after making order ready
    Given I tap on 'All' tab and search for 'readyOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'readyOrder' OrderID and 'confirm' it
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    And I tap on 'Pickups' icon in bottom menu for cap
    When I search by customer name to sort the orders
    Then I should see 'readyOrder' orderId in PickUp tab with message 'Ready for pickup'