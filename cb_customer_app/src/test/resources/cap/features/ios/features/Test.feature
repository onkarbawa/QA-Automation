@test
Feature: iOS- Test

  Scenario Outline: Setting test environment for CAP
    Given I launch CAP application for the first time
    And I have selected test environment for cap
    And I am not signed into application cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  Scenario: Setting up user account with credit card
    Given I launch Curbside application for the first time
    And I have selected Experimental test environment
    And I am not signed into application
    And I am on 'Gilroy' location 'Stores' Screen

  @iOS @TCS014
  Scenario Outline: Substitution Order - All order out of stock (1 item, 1 quantity each)
    Given I launch CAP application
    And I have selected test environment for cap
    And I am not signed into application cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
  Examples:
  | account  | username             | password |
  | curbside | qaautomation_initium | curbside |


  Scenario: Setting up user account with credit card
    Given I launch Curbside application
    And I select 'CVS' retailer and search for 'CVS Indoor/Outdoor Allergy Relief'
