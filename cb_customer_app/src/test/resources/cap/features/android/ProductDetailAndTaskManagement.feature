@productDetailsAndTaskManagement
Feature: Android- Product detail and Task Management

  Scenario: Setting up user account with credit card
    Given I launch Curbside application
    And I have selected Experimental test environment
    And I am not signed into application
    And I tap on 'Account' button
    And I tap on 'Sign Up' button
    And I tap on 'Sign Up with Email' button
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    Then I should see my given information under Account Info
    And I add credit card information

  Scenario: Order placed to check - Product detail screen
    And I add any product to cart in 'Gilroy' location
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'productDetail'

  Scenario: Order placed to check - Claim button
    And I add any product to the cart from store
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'claimOrder'

  Scenario: Order placed to check - Item not available (one out of two)
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I select 2 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'outOfStock'

  Scenario: Order placed to check - Item not available (two out of two)
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I select 2 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'outOfStockAll'

  Scenario: Order placed to check - Insufficient Quantity
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'insufficientQnty'

  Scenario Outline: Setting test environment for CAP
    Given I launch Cap application
    And I have selected test environment for CAP
    And I am logged in to the CAP with "<account>", "<username>" and "<password>"
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @Android @TCS06
  Scenario: Product detail screen
    And I wait for Tasks to get loaded
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
    And I search for 'outOfStock' order id under Pickups tab
    And I validate 'outOfStock' order marked as 'Customer Action Needed'

  @Android @TCS09
  Scenario: Mark all items out of stock (2 items, 1 quantity each)
    Given I am at CAP home screen
    And I search for 'outOfStockAll' Order Id under 'All' tab and 'Claim' it
    And I look for 'outOfStockAll' Order Id under 'Mine' tab and 'tap' it
    And I mark 'all' items not available
    And I tap on 'Finish' button
    And I go to Cancelled pickups screen
    And I search by customer name to sort the orders
    And I search for 'outOfStockAll' order id under Cancelled Pickups tab

  @Android @TCS10
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
    And I search for 'insufficientQnty' order id under Cancelled Pickups tab