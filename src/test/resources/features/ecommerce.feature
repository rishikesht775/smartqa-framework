Feature: E-commerce Flow

Scenario: Add product to cart
  Given user is on login page
  When user logs in with valid credentials
  And user adds product to cart
  Then cart should be opened