@deliveryPromo
Feature: Android - Delivery Promo code

  Background: Setting test environment
    Given I launch Curbside application
    And I am currently in 'Palo Alto, CA' city
    And I have selected test environment

  @Android @C114991
  Scenario: Test
    Given I am not signed into application
    And I sign in into application using username "fusic.test1@gmail.com" and password "fusic@123"
    And I add any product to the cart from store
    And I tap on 'Cart' button