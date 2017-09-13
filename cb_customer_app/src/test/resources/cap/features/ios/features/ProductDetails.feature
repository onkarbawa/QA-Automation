@productDetails
Feature: iOS- Product Details

  Scenario: Setting test environment
    Given I launch Curbside application for the first time
    And I have selected Experimental test environment
    And I am not signed into application
    And I am on 'Gilroy' location 'Stores' Screen
    And I Sign-in with 'gilroy_cvs@curbside.com' and 'curbside'
    And I saw email on MyAccount page
    And My cart is empty
    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
    And I select 'CVS Allergy Relief 24 Hour Indoor/Outdoor' product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I store the value of OrderId

  Scenario Outline: Setting test environment for CAP
    Given I launch CAP application for the first time
    And I have selected test environment for cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @iOS @TCS06
  Scenario: Verify that on Product Details are shown with selected product from curbside
    Given I search for selected Order ID and claim it
    And I tap on Mine tab
    And I search for selected Order
    Then I should see product details as below for CAP