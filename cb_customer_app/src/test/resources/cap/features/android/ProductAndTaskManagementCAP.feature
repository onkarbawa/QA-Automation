@productAndTaskManagementCAP
Feature: Android | CAP EE | Product & Task Management

  @Android
  Scenario Outline: Setting test environment for CAP
    Given I launch Cap application
    And I am not signed in to the CAP
    And I have selected test environment for CAP
    And I am logged in to the CAP with "<account>", "<username>" and "<password>"
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @Android @TCS06
  Scenario: Product detail screen
    Given I wait for Tasks to get loaded
    And I search for 'productDetail' Order Id under 'All' tab and 'tap' it
    When I click on a product from the order list
    Then I should see product details on the screen

  @Android @TCS07
  Scenario: Validating Claim button functionality
    Given I am at CAP home screen
    When I search for 'claimOrder' Order Id under 'All' tab and 'claim' it
    Then I look for 'claimOrder' Order Id under 'Mine' tab and 'confirm' it

  @Android @TCS08
  Scenario: Mark first item out of stock and process second(2 items, 1 quantity each)
    Given I am at CAP home screen
    And I search for 'outOfStock' Order Id under 'All' tab and 'Claim' it
    And I look for 'outOfStock' Order Id under 'Mine' tab and 'tap' it
    And I mark '1' item not available
    And I tap on 2nd item Got It button
    And I scan the barcode that is visible on my screen
    And I pay and enter total price
    And I take the picture of receipt
    And I tap on 'Go To Pack' button
    And I tap on 'Finish Packing' button
    And I tap on 'Pickups' button
    And I search by customer name to sort the orders
    And I validate 'outOfStock' order marked as 'Customer Action Needed' under pickup tab

  @Android @TCS09
  Scenario: Mark all items out of stock (2 items, 1 quantity each)
    Given I am at CAP home screen
    And I search for 'outOfStockAll' Order Id under 'All' tab and 'Claim' it
    And I look for 'outOfStockAll' Order Id under 'Mine' tab and 'tap' it
    And I mark 'all' items not available
    And I tap on 'Finish' button
    And I go to Cancelled pickups screen
    And I search by customer name to sort the orders
    Then I search for 'outOfStockAll' order id under Cancelled Pickups tab and 'confirm' it

  @Android @TCS11
  Scenario: Mark item Quantity not available (1 item, 2 quantities)
    Given I am at CAP home screen
    And I search for 'insufficientQnty' Order Id under 'All' tab and 'Claim' it
    And I look for 'insufficientQnty' Order Id under 'Mine' tab and 'tap' it
    And I tap on 'Issue' button
    And The total number of quantity available is '1'
    And I tap on 'Done' button
    And I tap on 'Go To Payment' button
    And I scan the barcode that is visible on my screen
    And I pay and enter total price
    And I take the picture of receipt
    And I tap on 'Go To Pack' button
    And I tap on 'Finish Packing' button
    And I tap on 'Pickups' button
    And I search by customer name to sort the orders
    Then I search for 'insufficientQnty' order id under Pickups tab and 'confirm' it

  @Android @TCS12 @TCS16
  Scenario: Process standard order (1 item, 1 quantities)
    Given I am at CAP home screen
    And I search for 'standardOrder' Order Id under 'All' tab and 'Claim' it
    And I look for 'standardOrder' Order Id under 'Mine' tab and 'tap' it
    And I tap on 'Got It' button
    And I scan the barcode that is visible on my screen
    And I pay and enter total price
    And I take the picture of receipt
    And I tap on 'Go To Pack' button
    And I tap on 'Finish Packing' button
    And I tap on 'Pickups' button
    And I search by customer name to sort the orders
    When I search for 'standardOrder' order id under Pickups tab and 'tap' it
    Then The order status should be 'Ready for Pickup'
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    And I tap on 'OK' button
    And I tap on 'Pickups' button
    When I search by customer name to sort the orders
    Then I confirm 'standardOrder' order is not present under Pickups tab

  @Android @TCS13
  Scenario: Verify cancelled pickup order (1 item, 1 quantities)
    Given I am at CAP home screen
    And I search for 'cancelledPickup' Order Id under 'All' tab and 'Claim' it
    And I look for 'cancelledPickup' Order Id under 'Mine' tab and 'tap' it
    And I tap on 'Got It' button
    And I scan the barcode that is visible on my screen
    And I pay and enter total price
    And I take the picture of receipt
    And I tap on 'Go To Pack' button
    And I tap on 'Finish Packing' button
    And I tap on 'Pickups' button
    And I search by customer name to sort the orders
    And I search for 'cancelledPickup' order id under Pickups tab and 'tap' it
    And The order status should be 'Ready for Pickup'
    And I tap on 'Cancel This Pickup' button
    And I tap on 'Cancel Pickup' button
    And I tap on 'Cancel - Unable To Verify ID' button
    When I search for 'cancelledPickup' Order Id under 'All' tab and 'tap' it
    Then I should see order title as 'Cancelled Pickup'

  @Android @TCS14
  Scenario: Accept substitution order - order out of stock(1 items, 1 quantity)
    Given I am at CAP home screen
    And I search for 'substitution' Order Id under 'All' tab and 'Claim' it
    And I look for 'substitution' Order Id under 'Mine' tab and 'tap' it
    And I mark 'all' items not available
    And I tap on 'Finish' button
    When I tap on 'Pickups' button
    And I search by customer name to sort the orders
    Then I validate 'substitution' order marked as 'Customer Action Needed' under pickup tab
    Given I launch Curbside application
    And I am on Home Screen
    And I tap on 'Account' button
    When I tap on 'My Orders' button
    Then I review the order and 'accept' the substitution
    Given I launch Cap application
    And I am at CAP home screen
    And I wait for Tasks to get loaded
    And I search for 'substitution' Order Id under 'All' tab and 'Claim' it
    And I look for 'substitution' Order Id under 'Mine' tab and 'tap' it
    And I tap on 'Got It' button
    And I scan the barcode that is visible on my screen
    And I pay and enter total price
    And I take the picture of receipt
    And I tap on 'Go To Pack' button
    And I tap on 'Finish Packing' button
    And I tap on 'Pickups' button
    And I search by customer name to sort the orders
    When I search for 'substitution' order id under Pickups tab and 'tap' it
    Then The order status should be 'Ready for Pickup'
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    And I tap on 'OK' button

  @Android @TCS15
  Scenario: Decline substitution order - order out of stock(2 items, 1 quantity each)
    Given I am at CAP home screen
    And I search for 'declineSubstitution' Order Id under 'All' tab and 'Claim' it
    And I look for 'declineSubstitution' Order Id under 'Mine' tab and 'tap' it
    And I mark '1' item not available
    And I tap on 2nd item Got It button
    And I scan the barcode that is visible on my screen
    And I pay and enter total price
    And I take the picture of receipt
    And I tap on 'Go To Pack' button
    And I tap on 'Finish Packing' button
    When I tap on 'Pickups' button
    And I search by customer name to sort the orders
    Then I validate 'declineSubstitution' order marked as 'Customer Action Needed' under pickup tab
    Given I launch Curbside application
    And I am on Home Screen
    And I tap on 'Account' button
    When I tap on 'My Orders' button
    Then I review the order and 'decline' the substitution
    Given I launch Cap application
    And I am at CAP home screen
    And I wait for Tasks to get loaded
    And I tap on 'Pickups' button
    And I search by customer name to sort the orders
    When I search for 'declineSubstitution' order id under Pickups tab and 'tap' it
    Then The order status should be 'Ready for Pickup'
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    And I tap on 'OK' button

  @Android @TCS24
  Scenario: Verify Hazmat order symbol
    Given I am at CAP home screen
    When I search for 'hazmat' Order Id under 'All' tab and 'confirm' it
    Then I should see 'hazmat' order has 'hazmat' symbol under Tasks-All tab
    When I search for 'hazmat' Order Id under 'All' tab and 'tap' it
    Then I should see 'hazmat' symbol inside the order
    And I tap on 'Claim' button
    And I tap on 'Close' button
    When I search for 'hazmat' Order Id under 'Mine' tab and 'confirm' it
    Then I should see 'hazmat' order has 'hazmat' symbol under Tasks-Mine tab
    And I look for 'hazmat' Order Id under 'Mine' tab and 'tap' it
    And I tap on 'Got It' button
    And I scan the barcode that is visible on my screen
    And I pay and enter total price
    And I take the picture of receipt
    And I tap on 'Go To Pack' button
    And I tap on 'Finish Packing' button
    And I tap on 'Pickups' button
    And I search by customer name to sort the orders
    When I search for 'hazmat' order id under Pickups tab and 'confirm' it
    Then I should see 'hazmat' order has 'hazmat' symbol under Pickups tab