@map
Feature: iOS | CAP-CVS | SE | Map

  Scenario Outline: Setting test environment for CAP
    Given I launch CAP-CVS application for the first time
    And I have selected test environment for cap
    And I am not signed into application cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @iOS @TCS07
  Scenario: Verify store location is displayed in map (Map View)
    Given I tap on 'Pickups' icon in bottom menu for cap
    And I tap on 'Map' button
    Then I should see 'CVS, 1st Street, Gilroy' store on Map

  @iOS @TCS08
  Scenario: Verify store location is displayed in map (Satellite View)
    Given I tap on 'Satellite View' button
    Then I should see 'CVS, 1st Street, Gilroy' store on Map
