@RunWith
Feature: The notifications can be retrieved

    Scenario: Client opens notifications
        Given I have opened Google Chrome
        When the client calls /notifications
        Then the title is Notifications