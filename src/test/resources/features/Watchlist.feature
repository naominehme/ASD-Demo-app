@RunWith
Feature: The watchlist can be retrieved

    Scenario: Client opens the watchlist
        Given I have opened Google Chrome
        When the client calls /watchlist
        Then the title is Watchlist

    Scenario: Client opens the watchlist with existing properties and preferences
        Given I have opened Google Chrome
        Given I am using the Test User
        When the client calls /watchlist
        And the watchlist properties are populated
        And the watchlist preferences are populated

    Scenario: Client opens the watchlist with no properties and preferences
        Given I have opened Google Chrome
        Given I am using the Empty Test User
        When the client calls /watchlist
        And the watchlist properties are not populated
        And the watchlist preferences are not populated

    Scenario: Client submits add property form with valid values
         Given I have opened Google Chrome
         Given I am using the Test User
         When the client calls /watchlist
         When the add property form is submitted with valid values
         Then there should be no errors

    Scenario: Client submits add property form with invalid values
         Given I have opened Google Chrome
         Given I am using the Test User
         When the client calls /watchlist
         When the add property form is submitted with invalid values
         Then there should be errors

    Scenario: Client submits add preference form with invalid values
         Given I have opened Google Chrome
         Given I am using the Test User
         When the client calls /watchlist
         When the add preference form is submitted with invalid values
         Then there should be errors