@applicationLaunch
Feature: Android- Task Management

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
    And I save Order Id of the product and named as 'outOfStock'

  Scenario Outline: Setting test environment for CAP
    Given I launch Cap application for the first time
    And I have selected test environment for CAP
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @Android @C114937
  Scenario: Mark an item out of stock
    And I wait for Tasks screen to get loaded
    And I search for Order Id named as 'outOfStock' and claim it
    And I tap on Mine tab
    And I search for Order Id named as 'outOfStock' and tap it
    And I tap on 'Issue' button
    And I tap on Items not available toggle button
    And I tap on 'Done' button
    And I tap on 'Finish' button



