@orderDetails
Feature: iOS | CAP-CVS | SE | Orderdetails and Claimbutton Functionality

  @iOS @TCS18 @TCS26
  Scenario: Setting up user account with credit card
    Given I launch Curbside application
    And I have selected Experimental test environment
    And I am not signed into application
    And I am on 'Gilroy' location 'Stores' Screen
    And I Sign-in with 'delivery_capcvs@curbside.com' and '1234567890'
    And I saw email on MyAccount page
    And My cart is empty
    And I select 'CVS' retailer
    And I select 1no product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'orderDetails'

  @iOS
  Scenario Outline: Setting test environment for CAP-CVS
    Given I launch CAP-CVS application
    And I have selected test environment for cap
    And I am not signed into application cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @iOS @TCS18
  Scenario: Validating Claim button functionality
    Given I tap on 'All' tab and search for 'orderDetails' OrderID and 'claim' it
    And I tap on 'Mine' tab
    Then I look for 'orderDetails' OrderId with 'Release' button

  @iOS @TCS26
  Scenario: Verify that on Product Details screen
    And I tap on 'Mine' tab and search for 'orderDetails' OrderID and 'confirm' it
    Then I should see product details as below for CAP-CVS

  @iOS @TCS27
  Scenario: Verify Order Info - open info screen
    Given I tap on back button
    And I tap on 'Info' button
    Then I should see 'orderDetails' pickUpID and Handling Instructions
