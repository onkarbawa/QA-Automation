@deliveryOrder
Feature: iOS- Delivery Order Verify

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

  @iOS @TCS27
  Scenario: Order placed to check - Product detail & claim order screen
    And I select 'CVS' retailer
    And I select 1no product from list
#    And I select 'CVS Health Indoor/Outdoor Allergy Relief Tablets' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I select Curbside Pickup and delivery option
    And I select the delivery address as:"4 Palo Alto Square, Palo Alto,California 94306"
    And I tap on back button
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'uberOrder'

  @iOS @TCS27
  Scenario: Verify Uber delivery flow
    Given I search for 'uberOrder' Order ID and verify that 'Hazmat Symbol' is present
    And I tap on 'Mine' tab
    And I search for 'uberOrder' OrderID
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    Then I tap on 'Pickups' icon and search for 'uberOrder' OrderID and verify that 'Uber Symbol' is present
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'