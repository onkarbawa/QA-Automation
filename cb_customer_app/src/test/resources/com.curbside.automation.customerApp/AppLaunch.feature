@applicationLaunch
Feature: Check information's and functionality's on Applaunch in Customer app

  @C114937
  Scenario: Verify application is able to launch and checkmark is set to 'Always'
    Given I launch the Customer App
    And I clicked on Allow to send notifications
    And I click three times for Scroll left
    And I click on 'Get Started' button
    And I click on 'Ok with me' button on access landing page
    When I click on 'Allow Access Location' button
    Then I should see the 'Store Selection Page'
    And I click on Home button
    And I click on 'Settings' application
    And I click on Privacy button
    And I scroll down & click on Customer App to launch
    When I click on 'Location' & select 'On'
    Then I saw checkmark is set 'Always'

  @C114936
  Scenario: Verify click on 'Skip Intro' or 'Get Started' button should take you back to the Store Selection page
    Given I launch the Customer App
    And I click on question mark icon in the top left corner
    And I click on 'Skip Intro' button
    And I click three times for Scroll left
    When I click on 'Get Started' button
    Then I should see the 'Store Selection Page'

  @C114996
  Scenario: Verify user should able to use the Customer App again after turn on Location Services
    Given I click on Home button
    And I click on 'Settings' application
    And I scroll down & click on Customer App to launch
    And I click on 'Location' & select 'NEVER'
    And I click on 'Home' button from device
    And I click on Customer app to launch
  @Incomplete

  @C114997
  Scenario: Verify user should able to checkout after enabling background app refresh button
    Given I click on Home button
    And I click on 'Settings' application
    And I scroll down & click on Customer App to launch
    And I click on 'Background App Refresh' to toggle 'OFF'
    And I click on Home button
    And I launch the Customer App
    And I click on 'Retailer partner' at nears by screen
    When I click on 'Product'
    Then I able to view product landing page
    And I click on 'Add To Cart'
    And I click on Cart icon in bottom menu
    When I click on 'Place Order' button
    Then I checked I am not able to checkout from cart
    And I click on Home button
    And I click on 'Settings' application
    And I scroll down & click on Customer App to launch
    And I click on 'Background App Refresh' to toggle 'ON'
    And I click on Home button
    And I launch the Customer App
    And I click on Cart icon in bottom menu
    When I click on 'Place Order' button
    Then I checked I am able to checkout from cart

  @C114998  @Android
  Scenario: Verify retailer selection page should show up after allowing permissions
    Given I click on Home button
    And I click on 'Settings' application
    And I click on 'Privacy and emergency'
    And I click on 'Location'
    And I click on customer button to enable
    And I click on Home button
    And I launch the Customer App
    When I click on 'Allow' button
    Then the retailer selection page should show up