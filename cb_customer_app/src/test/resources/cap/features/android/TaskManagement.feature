@TaskManagement
Feature: Android- Product detail and Task Management

  @Android
  Scenario: Setting test environment for CAP
    Given I launch Cap application
    And I have selected test environment for CAP
    And I am not signed in to the CAP

  @Android
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

  @Android
  Scenario: Order placed to check - Product detail screen
    And I add any product to cart in 'Gilroy' location
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'substitutionAll'

  @Android
  Scenario Outline: Setting test environment for CAP
    Given I launch Cap application
    And I have selected test environment for CAP
    And I am logged in to the CAP with "<account>", "<username>" and "<password>"
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  #TODO have to see TCS09 will complete after bug gets resolved
  @Android @TCS14
  Scenario: Substitution Order - All order out of stock (2 items, 1 quantity each)
    Given I am at CAP home screen
    And I search for 'substitutionAll' Order Id under 'All' tab and 'Claim' it
    And I look for 'substitutionAll' Order Id under 'Mine' tab and 'tap' it
    And I mark 'all' items not available
    And I tap on 'Finish' button

  @Android
  Scenario: Placing order
    Given I launch Curbside application
    And I am on Home Screen
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 2 quantity of the product
    And I select 2 product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    And I save Order Id of the product and named as 'outOfStockPartial'
  @Android @TCS15
  Scenario: Substitution Order - All order out of stock (2 items, 2 quantity each)
    Given I am at CAP home screen
    And I search for 'substitutionAll' Order Id under 'All' tab and 'Claim' it
    And I look for 'substitutionAll' Order Id under 'Mine' tab and 'tap' it
    And I mark 'all' items not available
    And I tap on 'Finish' button
