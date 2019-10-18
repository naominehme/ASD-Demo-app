@RunWith
Feature: The property detail page can be viewed

	  Scenario: Users open the index
		Given I have opened Google1
        When the client calls /home1
        Then the title is index1
        
    Scenario: Users open the detail
		    Given I have opened Google1
        When the client calls /home1
        When the client calls /detail1
        Then the detail page display1
        
#    Scenario: Users top up
#		    Given I have opened Google1
#        When the client calls /home1
#        When the client calls /detail1
#        When the Users input number1
#        Then return message1
        
#    Scenario: Users top up with a invalid input
#		    Given I have opened Google1
#        When the client calls /home1
#        When the client calls /detail1
#        When the Users input letter1
#        Then return message1