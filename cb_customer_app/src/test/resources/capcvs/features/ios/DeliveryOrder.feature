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

  @iOS @TCS28
  Scenario: Order placed to check - Uber delivery flow icons and images
    And I select 'CVS' retailer
    And I select 1no product and save product price named as'uberOrderPrice'
#    And I select 1no product from list
#    And I select 'CVS Health Indoor/Outdoor Allergy Relief Tablets' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I select Curbside Pickup and delivery option
    And I select the delivery address as:"4 Palo Alto Square, Palo Alto,California 94306"
    And I tap on back button
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'uberOrder'

  @iOS @TCS29
  Scenario: Order placed to check - Bag count hazmat uber flow
    And I go to the shop screen to add any product
    And I select 'CVS' retailer
    And I select 1no product and save product price named as'hazmatOrderPrice'
#    And I select 1no product from list
#    And I select 'CVS Health Indoor/Outdoor Allergy Relief Tablets' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I select Curbside Pickup and delivery option
    And I select the delivery address as:"4 Palo Alto Square, Palo Alto,California 94306"
    And I tap on back button
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'hazmatUberOrder'

  @iOS
  Scenario Outline: Setting test environment for CAP
    Given I launch CAP-CVS application
    And I have selected test environment for cap
    And I am not signed into application cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @iOS @TCS28
  Scenario: Verify Uber delivery flow
    Given I search for 'uberOrder' Order ID and verify that 'uber symbol' is present
    And I tap on 'Mine' tab and search for 'uberOrder' OrderID and 'confirm' it
#    And I tap on 'Mine' tab
#    And I search for 'uberOrder' OrderID
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter POS Total'
    And I enter receipt stored price for product 'uberOrderPrice'
#    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    Then I tap on 'Pickups' icon and search for 'uberOrder' OrderID and verify that 'uber symbol' is present
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'

    #### TO DO (wait for Delivery Logos)

  @iOS @TCS29
  Scenario: Verify Hazmat Uber flow
    Given I tap on 'Tasks' icon in bottom menu for cap
    Given I search for 'hazmatUberOrder' Order ID and verify that 'hazmat symbol' is present
    And I tap on 'Mine' tab and search for 'hazmatUberOrder' OrderID and 'confirm' it
#    And I tap on 'Mine' tab
#    And I search for 'hazmatUberOrder' OrderID
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter POS Total'
    And I enter receipt stored price for product 'hazmatOrderPrice'
#    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    Then I tap on 'Pickups' icon and search for 'hazmatUberOrder' OrderID and verify that 'hazmat symbol' is present
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'

     #### TO DO (wait for Delivery Logos)