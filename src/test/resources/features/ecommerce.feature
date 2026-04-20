Feature: Hybrid E-commerce Flow

@selenium
Scenario: Complete checkout using Selenium
  Given user is on selenium login page
  When user logs in using selenium
  And user adds product to cart using selenium
  And user completes checkout using selenium
  Then order should be placed successfully using selenium

@playwright
Scenario: Complete checkout using Playwright
  Given user is on playwright login page
  When user logs in using playwright
  And user adds product to cart using playwright
  And user completes checkout using playwright
  Then order should be placed successfully using playwright