@taskManagement
Feature: iOS- Task Management 1

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