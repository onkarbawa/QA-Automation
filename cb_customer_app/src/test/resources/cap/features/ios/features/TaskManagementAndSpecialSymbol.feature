@taskManagementAndSpecialSymbol
Feature: iOS- Task Management and Special Symbol

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

  @iOS @TCS13
  Scenario: Order placed to check - Cancel pickup functionality
    And I select 'CVS' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'cancelPickUpOrder'

#  Scenario: Order placed to check - Process Hazmat order (exclamation mark with triangle)
#    And I go to the shop screen to add any product
#    And I select 'CVS' retailer and search for 'Hairspray Aerosol'
#    And I select 'Aussie Mega Aerosol Hairspray' product from list
#    And I add 1 quantity of the product
#    And I tap on 'Cart' icon in bottom menu
#    And I attempt to place an order
#    And I tap on Order In Progress
#    And I save Order Id of the product and named as 'hazmatOrder'

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

  @iOS @TCS13
  Scenario: Verify cancelled pickup order (1 item, 1 quantities)
    Given I tap on 'All' tab and search for 'cancelPickUpOrder' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'cancelPickUpOrder' OrderID and 'confirm' it
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search by customer name to sort the orders
    And I search for 'cancelPickUpOrder' OrderID
    And I tap on 'Cancel This Pickup' button
    And I tap on 'Cancel Pickup' button
    And I tap on 'Cancel - Customer No Show' button
    Then I should see 'cancelPickUpOrder' orderId in Tasks screen under 'Cancelled Pickup'

#  @iOS @TCS24
#  Scenario: Verify Hazmat order Symbol
#    Given I tap on 'Close' button
#    And I search for 'hazmatOrder' Order ID and verify that 'Hazmat Symbol' is present
#    And I tap on 'Mine' tab
#    And I search for 'hazmatOrder' OrderID
#    And I tap on 'Got It' button
#    And I scan Barcodes and tap on 'Show Barcodes' button
#    And I tap on 'Enter Receipt Total' and enter receipt total price
#    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
#    And I tap on 'Go To Pack' button
#    And I tap on 'Finish' button
#    And I ckecked order is ready
#    Then I tap on 'Pickups' icon and search for 'hazmatOrder' OrderID and verify that 'Hazmat Symbol' is present
#    And I tap on 'Packages Retrieved' button
#    And I tap on 'Begin Transfer' button
#    And I tap on 'Confirm' button
#    Then I should see alert 'Transfer complete.'
