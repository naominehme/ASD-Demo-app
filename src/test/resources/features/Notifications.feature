@RunWith
Feature: The watchlist can be retrieved

    Scenario: Client opens the watchlist
        Given I have opened Google Chrome
        When the client calls /notifications
        Then the title is Notifications