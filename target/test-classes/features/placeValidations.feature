Feature: Validating Place API's


@AddPlace @Regression
Scenario Outline: Verify if place is being Succesfully added using AddPlaceAPI

Given Add Place Payload "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And varify place Id created map "<name>" using "getPlaceAPI"

Examples:
|name    | language | address         |
|shubham | English  | Mumbai sector G |

@DeletePlace @Regression
Scenario: Verify if delete place funcationality is working

      Given DeletePlace Payload
      When user calls "deletePlaceAPI" with "Post" http request
      Then the API call got success with status code 200
      And "status" in response body is "OK"
      


