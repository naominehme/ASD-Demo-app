@RunWith
Feature: The notifications can be retrieved

    Scenario: Client opens notifications
        Given I have opened Google Chrome
        When the client calls /notifications
        Then the title is Notifications

    Scenario: Client sets notifications to enabled
        Given I have opened Google Chrome
        When the client calls /notifications
        When the client disables Notifications Enabled
        When the preferences are submitted
        Then the Notifications Enabled should be unchecked

    Scenario: Client sets notifications to enabled
        Given I have opened Google Chrome
        When the client calls /notifications
        When the client enables Notifications Enabled
        When the preferences are submitted
        Then the Notifications Enabled should be checked