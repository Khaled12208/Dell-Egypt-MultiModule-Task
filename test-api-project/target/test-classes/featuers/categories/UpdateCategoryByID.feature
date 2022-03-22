@Regression
@UpdateCategoryByID
Feature:update a category data

  @HappyScenario
  Scenario Outline: User try to update the category with valid data
    Given user wants to update a category using patch "/categories/{id}" with id "<id>"
    And   update category body content type is "application/json"
    When  User send a patch request to update category with "<requestBody>"
    Then  update category response status code is "200"
    And   update category response header contains "ETag" header
    And   update category response body matches "<responseBody>"
    Examples:
      | id           | requestBody                                                          | responseBody                                                         |
                                    #Valid Category request body
      | abcat0010000 | scenarios/updatecategory/UpdateCategory_AllFields-request.json       | scenarios/updatecategory/UpdateCategory_AllFields-response.json      |

  @NegativeScenarios
  Scenario: User try to update the category with invalid data Missing product mandatory items
    Given user wants to update a category using patch "/categories/{id}" with id "abcat0010000"
    When  User send a patch request to update category with "scenarios/updatecategory/UpdateCategory_MissingName-request.json"
    Then  update category response status code is "400"
    And   update category response body matches "scenarios/updatecategory/UpdateCategory-Error-response.json"
