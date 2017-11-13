@productAndTaskManagementCurbside
Feature: Android | Curbside | Product & Task Management place order

  @Android
  Scenario: Setting up user account with credit card
    Given I launch Curbside application for the first time
    And I have selected Experimental test environment
    And I am not signed into application
    And I tap on 'Account' button
    And I tap on 'Sign Up' button
    And I tap on 'Sign Up with Email' button
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    Then I should see my given information under Account Info
    And I add credit card information

  @Android @TCS06
  Scenario: Order placed to check - Product detail screen
    And I add any product to cart in 'Gilroy' location
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'productDetail'

  @Android @TCS07
  Scenario: Order placed to check - Claim button
    And I add any product to the cart from store
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'claimOrder'

  @Android @TCS08
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

  @Android @TCS09
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

  @Android @TCS11
  Scenario: Order placed to check - Insufficient Quantity
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'insufficientQnty'

  @Android @TCS12
  Scenario: Order placed to check - Insufficient Quantity
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'standardOrder'

  @Android @TCS13
  Scenario: Order placed to check - Insufficient Quantity
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'cancelledPickup'

  @Android @TCS14
  Scenario: Order placed to check - Accept substitution order
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'substitution'

  @Android @TCS15
  Scenario: Order placed to check - Decline substitution order
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I select 2 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'declineSubstitution'

  @Android @TCS24
  Scenario: Order placed to check - Process Hazmat order
    And I search for 'Gilroy' location
    And I select 'CVS' store and search for 'hairspray aerosol' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'hazmat'