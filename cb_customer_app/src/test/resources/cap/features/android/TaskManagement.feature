@applicationLaunch
Feature: Android- Task Management

  Scenario: Standard Orders
    Given I launch Curbside application
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
    Given I launch Cap application
    And I have selected test environment for CAP
    And I am logged in to the CAP with "<account>", "<username>" and "<password>"
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @Android @TCS07
  Scenario: Mark an item out of stock
    Given I wait for Tasks to get loaded
    When I search for 'outOfStock' Order Id under 'All' tab and 'claim' it
    Then I look for 'outOfStock' Order Id under 'Mine' tab and 'confirm' it



