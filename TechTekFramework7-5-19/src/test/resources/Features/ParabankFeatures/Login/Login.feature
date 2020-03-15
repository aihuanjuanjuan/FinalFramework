Feature: parabank login feature



@ParabankLogin @REGRESSION
Scenario Outline: Successful Login with Login API


	Then execute successful Parabank login "<Testname>"
	
	Examples:
	
	|Testname       |
	|SuccessfulLogin|
	
	
