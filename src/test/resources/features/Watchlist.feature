@RunWith
Feature: the watchlist can be retrieved
  # A simple get scenario
  Scenario: client makes call to GET /watchlist
    Given I have opened the browser
    When the client calls /watchlist
    Then the title is Watchlist