@productDetailsAndTaskManagement
Feature: iOS- Product Details

  Scenario: Setting test environment
    Given I launch Curbside application for the first time
    And I have selected Experimental test environment
    And I am not signed into application
    And I am on 'Gilroy' location 'Stores' Screen
    And I tap on 'My Account' icon in bottom menu
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    And I add credit card information
#    And I Sign-in with 'gilroy_cvs@curbside.com' and 'curbside'
#    And I saw email on MyAccount page
#    And I checked there is any user attention message
#    And My cart is empty
    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
    And I select 1no product from 'CVS' store
#    And I select 'CVS Indoor/Outdoor Allergy Relief Cetirizine Hydrochloride Tablets, 10Mg, 14 CT' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'claimOrder'
    And I go to the shop screen to add any product
    And I select 1no product from 'CVS' store
#    And I select 'CVS Indoor/Outdoor Allergy Relief Cetirizine Hydrochloride Tablets, 10Mg, 14 CT' product from list
    And I add 1 quantity of the product
###    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
    And I select 2no product from 'CVS' store
#    And I select 'CVS Health Indoor/Outdoor Allergy Relief Cetirizine Hydrochloride Tablets 10Mg, 120 CT' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'outOfStock'
    And I go to the shop screen to add any product
    And I select 1no product from 'CVS' store
#    And I select 'CVS Indoor/Outdoor Allergy Relief Cetirizine Hydrochloride Tablets, 10Mg, 14 CT' product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'insufficientQuantity'
    And I go to the shop screen to add any product
    And I select 1no product from 'CVS' store
#    And I select 'CVS Indoor/Outdoor Allergy Relief Cetirizine Hydrochloride Tablets, 10Mg, 14 CT' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'readyPickUpOrder'

  Scenario Outline: Setting test environment for CAP
    Given I launch CAP application for the first time
    And I have selected test environment for cap
    And I am not signed into application cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @iOS @TCS07
  Scenario: Validating Claim button functionality
    Given I search for 'claimOrder' Order ID and claim it
    And I tap on 'Mine' tab
#    And I should see claim product in Mine tab list with 'Unclaim' button
    Then I look for 'claimOrder' Order Id under 'Mine' tab

  @iOS @TCS06
  Scenario: Verify that on Product Details are shown with selected product from curbside
    And I search for 'claimOrder' OrderID
    Then I should see product details as below for CAP

  @iOS @TCS08
  Scenario: Mark an item out of stock
    And I tap on back button
    And I tap on 'Issue' button
    And I turn 'ON' 'Item not Available'
    And I tap on 'Done' button
    And I tap on 'Finish' button
    Then I should see 'claimOrder' orderId in Task tab with 'Needs customer attention'


  @iOS @TCS09
  Scenario: Verify that when we Mark All item out of stock and Order should be cancelled
    Given I tap on 'Close' button
    And I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab
    And I search for 'outOfStock' Order ID and claim it
    And I tap on 'Mine' tab
    And I search for 'outOfStock' OrderID
    And I mark all items as 'Item not Available'
    Then I should see 'outOfStock' orderId in Cancelled pickups with 'Pickup is Cancelled'

  @iOS @TCS11
  Scenario: Verify that when change the quantity of multiple items it shows user review message and updated item quantity
    Given I tap on 'Close' button
    And I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab
    And I search for 'insufficientQuantity' Order ID and claim it
    And I tap on 'Mine' tab
    And I search for 'insufficientQuantity' OrderID
    And I tap on 'Issue' button
    And I enter insufficient quantity '1'
    And I 'Go To Payment' screen
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I tap on 'Pickups' icon in bottom menu for cap
    And I should see 'insufficientQuantity' orderId in Task tab with 'Needs customer attention'
    Then I should see total Order quantity '2' and updated order quantity '1'

  @iOS @TCS12
  Scenario: Verify that when we complete order it shows Ready message
    Given I tap on 'Close' button
    And I tap on 'Tasks' icon in bottom menu for cap
    And I tap on 'All' tab
    And I search for 'readyOrder' Order ID and claim it
    And I tap on 'Mine' tab
    And I search for 'readyOrder' OrderID
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I tap on 'Pickups' icon in bottom menu for cap
    And I search for 'readyOrder' OrderID
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'

