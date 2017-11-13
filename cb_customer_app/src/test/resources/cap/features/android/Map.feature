@map
Feature: Android | CAP EE | Map verification

  @Android
  Scenario Outline: Setting test environment for CAP
    Given I launch Cap application for the first time
    And I have selected test environment for CAP
    And I am not signed in to the CAP
    And I am logged in to the CAP with "<account>", "<username>" and "<password>"
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @Android @TCS19
  Scenario: verify stores are visible on map - Normal view
    Given I wait for Tasks to get loaded
    And I tap on 'Pickups' button
    When I tap on Map button on visibility
    Then I should see stores location on the map

  @Android @TCS20
  Scenario: verify stores are visible on map - Satellite view
    When I tap on 'Satellite View' button
    Then I should see stores location on the map
