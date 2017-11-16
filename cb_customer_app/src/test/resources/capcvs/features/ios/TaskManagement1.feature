@taskManagement1
Feature: iOS- Task Management - 1

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

  @iOS @TCS19
  Scenario: Order placed to check - Insufficient Quantity
    And I select 'CVS' retailer
    And I select 1no product and save product price named as'insufficient'
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'insufficientQuantity'

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


  @iOS @TCS19
  Scenario: Mark item Quantity not available (1 item, 2 quantities)
    Given I tap on 'All' tab and search for 'insufficientQuantity' OrderID and 'claim' it
    And I tap on 'Mine' tab and search for 'insufficientQuantity' OrderID and 'confirm' it
    And I tap on 'Issue' button
    And I enter insufficient quantity '1'
    And I 'Go To Payment' screen
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter POS Total'
    And I enter receipt stored price for product 'insufficient'
#    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search by customer name to sort the orders
    And I should see 'insufficientQuantity' orderId in PickUp tab with message 'Customer Action Needed'
    Then I should see total Order quantity '2' and updated order quantity '1'