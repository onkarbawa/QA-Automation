@liveOrder
Feature: Android- Live order

  @Android @C114962
  Scenario Outline: End to End - Signup EMail, Signup SMS and Pickup order EMail verifications
    Given I launch Curbside application for the first time
    And I wait for application to be launched
    And I swipe left 2 times on intro page
    And I tap on 'Get Started' button
    And I tap on 'OK' button on 'location access' page
    When I accept location access alert
    Then I should see 'Nearby stores' landing page
    And I have selected test environment
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select 'CVS' store and search for 'cvs products' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    And I tap on Place order button
    And I tap on 'Sign Up' button
    And I tap on 'Sign Up with Email' button
    And I check there is no latest SMS from Curbside
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    Then I should see my given information under Account Info
    Then I will receive welcome SMS from Curbside app
    And I tap on PaymentInfo button on Account page
    And I should receive 'Welcome' email on 'signup' EmailID
    And I tap on Add New Card button on Payment info page
    When I add credit card information as '<card_number>', '<card_expiry>', '<ccv>', '<first_name>', '<last_name>', '<card_street_address>', '<apt_suite>','<card_city>', '<card_state>', '<card_zip>'
    Then I should see the card added to Payment info
    And I tap on 'Account' button
    And I tap on 'Loyalty Cards' button
    And I tap on 'Add New Card' button
    When I add an ExtraCare Card numbered '87676478652876'
    Then I should see ExtraCare Card info on Loyalty Cards screen
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    Then I should receive 'In-Progress pickup' email on 'signup' EmailID
    Examples:
      | card_number      | card_expiry | ccv | first_name | last_name | card_street_address | apt_suite | card_city | card_state | card_zip |
      | 4012000077777777 | 1218        | 123 | John       | miller    | 1 Infinite Loop     |           | Cupertino | California | 95014    |

  @Android @C114963
  Scenario Outline: End to End - 'UNLIMITED' promo code and Pickup order EMail verifications
    Given I am not signed into application
    And I sign in into application using username "end2endpickup@mailinator.com" and password "1234567890"
    And I am on 'Palo Alto' location 'Stores' Screen
    And I cancel 'all' orders
    And My cart is empty
    And The mail box of userID "end2endpickup" is empty
    And I select 'CVS' store and search for 'Sensodyne fresh' product
    And I select 1 product from list
    And I add 2 quantity of the product
    And I tap on 'Cart' button
    And I tap on Enter promo code link
    When I apply promo code "<Promo Code>"
    Then I should see promo code is not applied
    And I select 'CVS' store and search for 'Prilosec otc' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    And I tap on Enter promo code link
    When I apply promo code "<Promo Code>"
    Then I should see promo code is applied and discount is given as per '<Discount Type>'
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    Then I should receive 'In-Progress pickup' email on 'end2endpickup' EmailID
    Examples:
      | Promo Code | Discount Type |
      | UNLIMITED  | Unlimited     |

  @Android @C114964
  Scenario: End to End - Delivery order EMail verifications
    Given I am not signed into application
    And I sign in into application using username "end2enddelivery2@mailinator.com" and password "1234567890"
    And I am on 'Palo Alto' location 'Stores' Screen
    And My cart is empty
    And The mail box of userID "end2enddelivery" is empty
    And I select 'CVS' store and search for 'Sensodyne fresh' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    And I tap on 'Curbside Pickup' button
    And I tap on Delivery with UBER button
    And I select the delivery address as, street:"Palo Alto Square", city:"Palo Alto",state:"CA"
    And I tap on back button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    Then I should receive 'In-Progress delivery' email on 'end2enddelivery2' EmailID