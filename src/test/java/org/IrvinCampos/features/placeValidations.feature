Feature: Validating Place API's
  @AddPlace
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "Post" http request
    Then the API call is success with status code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"
    And verify place id created maps to "<name>" using "getPlaceAPI"

    Examples:
    | name   | language | address            |
    |AAhouse | English  | World Cross Center |
#    |Cozy    | English  | Madrid             |
@DeletePlace
  Scenario: Verify if Delete place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "Post" http request
    Then the API call is success with status code 200
    And "status" in response is "OK"



