@myAccount
Feature: IOS|CAP Sephora|Login & Logout

  @iOS
  Scenario: Setting test environment
    Given I launch CAP Sephora application for the first time
    And I have selected test environment for CAP Sephora

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