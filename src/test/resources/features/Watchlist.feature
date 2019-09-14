@RunWith
Feature: The watchlist can be retrieved
  # A simple get scenario
  Scenario: Client makes call to GET /watchlist using Google Chrome
    Given I have opened Google Chrome
    Given I am using the Test User
    When the client calls /watchlist
    Then the title is Watchlist
    And the watchlist properties are populated
    And the watchlist preferences are populated