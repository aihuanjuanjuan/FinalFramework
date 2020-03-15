Feature: People and Organization feature 


Scenario Outline: Add Person Successfully 

	User should be able to add a new person
	
	Given when i am logged in the homepage 
	Then i click People and organization link 
	Then i fill the form and click save 
	And i should then be navigated to the new added persons profile page 
	When i click People and Organization link 
	Then the new added user should be added to list of other added persons 
	
	
	
	Examples: 
		| Testname 			       |
		| AddNewPersonSuccessfully |
		
	