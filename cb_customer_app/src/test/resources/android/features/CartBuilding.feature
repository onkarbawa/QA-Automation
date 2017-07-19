@cartBuilding
Feature: Android- Cart Building
  Scenario: Setting test environment
    Given I launch Curbside application
    And I am currently in 'Palo Alto, CA' city
    And I have selected test environment

    #TODO working on it, Please donot add this in testng-android.xml file
  @Android @C114945
  Scenario: Verify products display in landing page
    Given I Sign-in with cart building credentials
    And I am on 'Palo Alto' location 'Stores' Screen
    And My cart is empty
    And I tap on 'Shop' button
    And I select a store
    And I tap on product from the list
    And I add product in cart
    And I saw added product in cart