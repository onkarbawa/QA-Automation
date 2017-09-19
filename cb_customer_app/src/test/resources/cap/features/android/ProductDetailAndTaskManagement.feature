@productDetailsAndTaskManagement
Feature: Android- Product detail and Task Management

  Scenario: Standard Orders
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
    And I add any product to cart in 'Gilroy' location
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'claimOrder'
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I select 2 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'outOfStock'
    And I add any product to the cart from store
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'productDetail'

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
    Given I wait for Tasks to get loaded
    When I search for 'claimOrder' Order Id under 'All' tab and 'claim' it
    Then I look for 'claimOrder' Order Id under 'Mine' tab and 'confirm' it

  @Android @TCS08
  Scenario: Mark first item out of stock and process second(2 items)
    And I search for 'outOfStock' Order Id under 'All' tab and 'Claim' it
    And I look for 'outOfStock' Order Id under 'Mine' tab and 'tap' it
    And I mark '1' item not available
    And I tap on 'Finish' button
    And I tap on 'Pickups' button
    And I search by customer name to sort the orders
    And I search for 'outOfStock' order id under Pickups tab
    And I validate 'outOfStock' order marked as 'Customer Action Needed'

  @Android @TCS09
  Scenario: Mark all items out of stock (2 items)
    And I search for 'outOfStockAll' Order Id under 'All' tab and 'Claim' it
    And I look for 'outOfStockAll' Order Id under 'Mine' tab and 'tap' it
    And I mark 'all' items not available
    And I tap on 'Finish' button
    And I go to Cancelled pickups screen
    And I search by customer name to sort the orders
    And I search for 'outOfStockAll' order id under Cancelled Pickups tab
