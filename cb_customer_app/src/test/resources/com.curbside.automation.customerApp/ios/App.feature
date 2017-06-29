@applicationLaunch
Feature: Check information's and functionality's on Applaunch in Customer app


  @C114936
  Scenario: Verify click on 'Skip Intro' or 'Get Started' button should take you back to the Store Selection page
  Given I launch the Customer App
  And I clicked on Allow to send notifications
  And I click on 'Skip Intro' button
  And I click on 'Ok with me' button on access landing page
  And I click on 'Allow Access Location' button
  And I click on question mark icon in the top left corner
  And I click "3" times for Scroll left
  When I click on 'Get Started' button
  Then I should see the 'Store Selection Page'
  And I close the web driver session for ios