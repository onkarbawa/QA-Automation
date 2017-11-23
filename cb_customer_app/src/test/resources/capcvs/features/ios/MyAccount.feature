@myAccount
Feature: iOS | CAP-CVS | SE | Login and Logout

  @iOS
  Scenario: Setting test environment
    Given I launch CAP-CVS application
    And I have selected test environment for cap
    And I am not signed into application cap

  @iOS @TCS01
  Scenario Outline: Verify that on Logout User should brought back to login page
    Given I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    And I tap on 'My Account' icon in bottom menu for cap
    When I am not signed into application cap
    Then I should see 'Curbside Console' screen
    Examples:
      | account | username | password |
      | curbside     | qaautomation_initium   | curbside |