@specialSymbol
Feature: iOS | CAP-CVS | SE | Special Symbol

  @iOS
  Scenario: Setting up user account with credit card
    Given I launch Curbside application
    And I have selected Experimental test environment
    And I am not signed into application
    And I am on 'Gilroy' location 'Stores' Screen
    And I tap on 'My Account' icon in bottom menu
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    And I add credit card information

  @iOS @TCS30
  Scenario: Order placed to check - Age Restriction order Symbol (exclamation mark with triangle)
    And I select 'CVS' retailer and search for 'Hairspray Aerosol'
    And I select 'Aussie Mega Aerosol Hairspray' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'hazmatOrder'

  @iOS @TCS31
  Scenario: Order placed to check - Process Hazmat order (exclamation mark with triangle)
    And I go to the shop screen to add any product
    And I select 'CVS' retailer and search for 'Hairspray Aerosol'
    And I select 'Aussie Mega Aerosol Hairspray' product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' icon in bottom menu
    And I attempt to place an order
    And I tap on Order In Progress
    And I save Order Id of the product and named as 'hazmatOrder'

  @iOS
  Scenario Outline: Setting test environment for CAP
    Given I launch CAP application
    And I have selected test environment for cap
    And I am not signed into application cap
    And I enter "<account>", "<username>" and "<password>" for login
    And I tap on 'Login' button
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |

  @iOS @TCS30
  Scenario: Verify Age Restriction order Symbol
    Given I search for 'ageRestrictionOrder' Order ID and verify that 'AgeRestriction Symbol' is present
    And I tap on 'Mine' tab and search for 'ageRestrictionOrder' OrderID and 'confirm' it
#    And I tap on 'Mine' tab
#    And I search for 'hazmatOrder' OrderID
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
#    And I tap on 'Enter POS Total'
#    And I enter receipt stored price for product 'hazmatOrderPrice'
    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    Then I tap on 'Pickups' icon and search for 'ageRestrictionOrder' OrderID and verify that 'AgeRestriction Symbol' is present
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'

    #### TO DO (wait for Special Symbol Logos)

  @iOS @TCS31
  Scenario: Verify Hazmat order Symbol
    Given I tap on 'Tasks' icon in bottom menu for cap
    And I search for 'hazmatOrder' Order ID and verify that 'Hazmat Symbol' is present
    And I tap on 'Mine' tab and search for 'hazmatOrder' OrderID and 'confirm' it
#    And I tap on 'Mine' tab
#    And I search for 'hazmatOrder' OrderID
    And I tap on 'Got It' button
    And I scan Barcodes and tap on 'Show Barcodes' button
#    And I tap on 'Enter POS Total'
#    And I enter receipt stored price for product 'hazmatOrderPrice'
    And I tap on 'Enter Receipt Total' and enter receipt total price
    And I tap on 'Take Picture' button and 'Use Photo' to scan barcode
    And I tap on 'Go To Pack' button
    And I tap on 'Finish' button
    And I checked order is ready
    Then I tap on 'Pickups' icon and search for 'hazmatOrder' OrderID and verify that 'Hazmat Symbol' is present
    And I tap on 'Packages Retrieved' button
    And I tap on 'Begin Transfer' button
    And I tap on 'Confirm' button
    Then I should see alert 'Transfer complete.'

    #### TO DO (wait for Special Symbol Logos)
