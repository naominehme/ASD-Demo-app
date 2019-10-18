@RunWith
Feature: The property detail page can be viewed
        
    Scenario: Users open the detail
		    Given I have opened Google2
        When the client calls csservice
        When the client submit problem
        Then the return message2