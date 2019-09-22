@RunWith
Feature: The property detail page can be viewed

	  Scenario: Users open the index
		Given I have opened Google
        When the client calls /home
        Then the title is index
        
    Scenario: Users open the detail
		    Given I have opened Google
        When the client calls /home
        When the client calls /detail
        Then the detail page display
        
    Scenario: Users place a bid
		    Given I have opened Google
        When the client calls /home
        When the client calls /detail
        When the Users input number
        Then return message
        
    Scenario: Users place a bid with a invaild input
		    Given I have opened Google
        When the client calls /home
        When the client calls /detail
        When the Users input letter
        Then return message