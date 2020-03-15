Feature: A user can register to create a new account

		we are validatig a successful registration by a customer.
		
		As a user i want to create bank account with parabank
		
		
@REGISTRATION @PARABANK
Scenario Outline: Successful Registration

	in this scenario we are validating that a user can create his/her new account
	once the registration is submitted the user will be automatically logged in to the welcome page
	


	Given user fills out the registration form
	Then user should be logged in after submitting the registration form "<Testname>"

	

	Examples:
	
	|Testname 				  |
	|Successful_Registration  |
	
