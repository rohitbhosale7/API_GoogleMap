Feature: Validating Place API's

Scenario Outline: Verify if Place is being successfully added using addPlaceAPI
	Given Add Place payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And verify placeId created maps to "<name>" using "getPlaceAPI"
	
Examples: 

	|name            |language  |address	                 |
	|Frontline house |French-IN |29, side layout, cohen 09 |
	