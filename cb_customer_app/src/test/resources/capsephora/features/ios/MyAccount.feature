@myAccount
Feature: IOS | CAP Sephora | Install | Login & Logout

  @iOS @TCS01
  Scenario: Verify install app and notification popups
    Given I launch CAP Sephora application for the first time
    And I tap on 'Allow' button on 'Notification' popup
    And I tap on 'Allow' button on 'Location Services' popup
    And I tap on 'OK' button on 'Remote Notification' popup
    And I tap on 'OK' button on 'Remote Notification' popup
    And I tap on 'OK' button if displayed
    Then I should see 'Curbside Console' screen

  @iOS @TCS02
  Scenario Outline: Verify logout user should brought back to login page
    Given I launch CAP Sephora application
    And I have selected test environment for CAP Sephora
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    When I tap on 'My Account' icon in bottom menu for cap
    And I am not signed into application cap
    Then I should see 'Curbside Console' screen
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |