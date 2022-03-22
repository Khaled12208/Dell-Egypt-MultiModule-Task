@Regression
@GetCategoryByID
Feature: Get Category by ID

  @HappyScenario
  Scenario Outline: User try to get a Category with valid Category id
    Given user send a request to get category "/categories/{id}" with "<id>"
    Then category status code is "200"
    And  get category response body is "<response>"
    Examples:
      | id            | response                                                      |
            #Valid ID
      | abcat0101000  | scenarios/getcategorybyid/response-body_Id_abcat0101000.json  |

  @NegativeScenarios
  Scenario Outline: User try to get category with invalid category ID
    Given user send a request to get category "/categories/{id}" with "<id>"
    Then category status code is "404"
    And there is no "<record>" found for this category id
    Examples:
      | id   | record                        |
                  #Invalid Negative Value
      | -1   | No record found for id '-1'   |
                        #Invalid Zero Value
      |   0  | No record found for id '0'    |
                              #Invalid Numeric-String Value
      |  0S  | No record found for id '0S'   |
                                    #Invalid String Value
      |  AS  | No record found for id 'AS'   |