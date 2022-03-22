@Regression
@AddCategory
Feature: User Wants to add a new category

  @HappyScenario
  Scenario: User try to add the category with valid data
    Given user wants to add a category using post "/categories"
    And   new category body Content type is "application/json"
    When  User send a post request to set new category with unique ID and name "category"
    Then  new category response status code is "201"
    And   new category response header contains "ETag" header
    And   new category response body  should be is empty

  @NegativeScenarios
  Scenario Outline: User try to add category with invalid data request body parameters and valid content type
    Given user wants to add a category using post "/products"
    And   new category body Content type is "application/json"
    When  User send a post request to add category with "<requestBody>"
    Then  new category response status code is "<statusCode>"
    And   new category response body should match "<responseBody>"
    Examples:
   | requestBody                                                             | statusCode |responseBody                                                                        |
                                 #Invalid request body with Integer ID
   | scenarios/addnewcategory/invalid-body-id-int-request.json               | 400        |scenarios/addnewcategory/invalid-body-id-int-response.json                          |
                                 #Invalid request body with missing Category Name
   | scenarios/addnewcategory/invalid-body-missing-name-request.json         | 400        |scenarios/addnewcategory/invalid-body-missing-name-response.json                    |
                                 #Invalid request body missing category ID
   | scenarios/addnewcategory/invalid-body-missing-id-request.json           | 400        |scenarios/addnewcategory/invalid-body-missing-id-response.json                      |