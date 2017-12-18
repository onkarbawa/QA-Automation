@performance
Feature: iOS | CAP-CVS | SE | Performance

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

  @iOS @TCS38
  Scenario: Order placed to check - Cancelled pickup order removed immediately from pickups
    And I select 'CVS' retailer
    And I select 1no product and save product price named as'cancelReadyPickUpOrder'
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'cancelReadyOrder'

  @iOS @TCS38
  Scenario: Verify cancelled pickup order removed immediately from pickups(1 item, 1 quantities)
    Given I tap on 'All' tab and search for 'cancelReadyOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'cancelReadyOrder' OrderID and 'confirm' it
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter POS Total'
    And I enter receipt stored price for product 'cancelReadyPickUpOrder'
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
#    And I tap on 'Pickups' icon in bottom menu for cap
#    And I search by customer name to sort the orders
#    And I search for 'cancelPickUpOrder' OrderID
    Then I should see 'cancelReadyOrder' orderId in PickUp tab with message 'Ready for pickup'
    And I tap on 'Cancel This Pickup' button
    And I tap on 'Cancel Pickup' button
    And I tap on 'Cancel - Customer No Show' button
    When I search by customer name to sort the orders
    Then I confirm 'cancelReadyOrder' orderID is not present under Pickups tab


