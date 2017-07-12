@signUp
Feature: Verify curbside signup flow

  #TODO
  @C114959
  Scenario: Add loyalty card
    Given I launch Curbside application for the first time
    And I wait for 'Skip Intro' button
    And I am on Home Screen
    And I tap on 'Account' button
    And I tap on 'Loyalty Cards' button
    And I tap on 'Add a new card'
    And I add an ExtraCare Card numbered '87676478652876'
    Then I should see ExtraCare Card info on Loyalty Cards screen
    And I add any product to cart in 'Palo Alto Transit Center' location
    When I go to Cart screen
    Then I should see loyalty card info on cart screen