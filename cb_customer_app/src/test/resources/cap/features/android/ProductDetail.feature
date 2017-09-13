@productDetail
Feature: Android- Product Detail

  Scenario: Standard Orders
    Given I launch Curbside application for the first time
    And I have selected Experimental test environment
    And I sign in into application using username "capautomation@test.com" and password "1234567890"
    Then I should be logged in to the application
    And My cart is empty
    And I add any product to cart in 'Gilroy' location
    And I cancel 'all' orders
    And I tap on 'Cart' button
    And I tap on Place order button
    And I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'productDetail'

  Scenario Outline: Setting test environment for CAP
    Given I launch Cap application
    And I have selected test environment for CAP
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @Android @TCS06
  Scenario: Detail screen
    And I wait for Tasks to get loaded
    And I search for Order Id named as 'productDetail' and 'tap' it
    When I click on a product from the order list
    Then I should see product details on the screen