@orderFlow
Feature: Android- Order flow verification

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment

  @Android @C114951
  Scenario: Cancelled order should appear in cancelled section
    Given I am not signed into application
    And I sign in into application using username "orderflow@test.com" and password "1234567890"
    And I am on 'Palo Alto' location 'Stores' Screen
    And I cancel 'all' orders
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    And I tap on Place order button
    And I should see the successful placed order notification on the screen
    When I cancel 'latest' order
    Then I should see the order under cancelled tab
