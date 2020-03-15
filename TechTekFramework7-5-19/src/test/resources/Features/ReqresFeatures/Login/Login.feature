Feature: login feature

	when a user a logs in with valid credentials
	user will get a token in the response


@ReqresLogin @MI-4529 @REGRESSION @REQRES
Scenario Outline: Successful Login with Login API

	user logs in with valid username and password and user will get a token in the response

	Then execute successful login "<Testname>"
	
	Examples:
	
	|Testname       |
	|SuccessfulLogin|
	
	
	

	
	
