Feature: Application Login
@AddPlace
  Scenario Outline: Verify if Place is being successfully add to AddPlaceAPI
    Given Add Place Payload with <accuracy> "<name>" "<language>" "<address>"
    When User call "AddPlaceAPI" with "POST" http Request
    Then The API call got successfull with status code "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify if place_Id create maps to "<name>" using "GetPlaceAPI"


    Examples:
      |accuracy | name                              | language        | address                |
      | 30      | Test-Passed Carlos Address        | English         | Boulevard Manaus Avenue|
#      | 45      |Test-Passed Antoine                | Spanish         | Avena Paulista         |
@DeletePlace
  Scenario: Verify if Delete functionality is working
    When Delete Place Payload
    When User call "DeletePlaceAPI" with "Post" http Request
    Then The API call got successfull with status code "200"
    And "status" in response body is "OK"
