Feature: Hybrid E-commerce Flow

@selenium
Scenario: Add product to cart using Selenium
  Given user is on selenium login page
  When user logs in using selenium
  And user adds product to cart using selenium
  Then cart should have 1 item using selenium
  
@playwright
Scenario: Add product to cart using Playwright
  Given user is on playwright login page
  When user logs in using playwright
  And user adds product to cart using playwright
  Then cart should have 1 item using playwright