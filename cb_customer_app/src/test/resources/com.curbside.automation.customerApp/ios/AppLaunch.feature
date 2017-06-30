@applicationLaunch
Feature: Check information's and functionality's on Applaunch in Curbside app

  @C114936
  Scenario: Verify click on 'Skip Intro' or 'Get Started' button should take you back to the Store Selection Screen
    Given I launch the Curbside App
    And I accept Allow to send notifications
    And I click on 'Skip Intro' button
    And I tap on 'Ok with me' button on access landing screen
    And I accept 'Allow Access Location'
    And I tap on question mark icon in the top left corner
    And Swipe left "2" times
    When I tap on 'Get Started' button
    Then I should see the 'Store Selection Screen Screen'

  @C114937
  Scenario: Verify application is able to launch and location is set to 'Always'
    Given I launch the Curbside App
    And I accept Allow to send notifications
    And Swipe left "2" times
    And I tap on 'Get Started' button
    And I tap on 'Ok with me' button on access landing screen
    When I accept 'Allow Access Location'
    Then I should see the 'Store Selection Screen Screen'
    And I tap on 'Settings' application
    And Scroll to Curbside App
    When I tap on 'Location'
    Then I saw checkmark is set 'Always'


  @C114996
  Scenario: Verify user should able to use the Curbside App again after turn on Location
    Given I tap on 'Settings' application
    And Scroll to Curbside App
    And I tap on 'Location'
    And I tap on "Never"
    And I launch the Curbside App
    And I accept Allow to send notifications
    Then I should see 'Curbside Settings Page'
    And I click on 'Settings' on curbside page
    And I tap on 'Location'
    And I tap on "Always"
    When I launch the Curbside App
    Then I should see the 'Store Selection Screen Screen'






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
