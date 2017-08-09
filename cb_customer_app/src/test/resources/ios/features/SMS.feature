@sms
Feature: iOS- SMS Flow

  Scenario: Setting test environment
    Given I launch Curbside application for the first time
    And I have selected test environment
    And I am on 'Palo Alto' location 'Stores' Screen

  @iOS @C114969
  Scenario: Verify
    Given I am not signed into application
    And I Sign-in with 'sms_notification@ios.com' and 'curbside'
    And I get the previous message count
    And My cart is empty
    And I select 'Westfield Valley Fair, at 2855 Stevens Creek Blvd' retailer and search for 'Salmon Oil'
    And I select 1no product from list
#    And I select 'CVS, at 855 El Camino Real' retailer and search for 'Cvs Indoor/Outdoor'
#    Andnd I select 'Cvs Allergy Relief 24 Hour Indoor/outdoor' product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I should see sms notification has been received