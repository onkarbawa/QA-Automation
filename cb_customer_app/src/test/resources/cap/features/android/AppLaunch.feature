@applicationLaunch
Feature: Android- Application Launch

	Curbside tutorial is prompted when app is launched first time and verify tutorial flow

  @Android @C114937
  Scenario: Tapping 'Get Started' button should take you to Store Selection Page
    Given I launch Cap application for the first time
