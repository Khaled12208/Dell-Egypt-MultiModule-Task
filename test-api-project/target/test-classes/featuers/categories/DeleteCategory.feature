@Regression
@DeleteCategory
Feature: Delete a category using the category ID

  @HappyScenario
  Scenario: User will try to delete a product using a valid category ID
    Given A PRE-created category with data "scenarios/deletecategorybyid/valid-fullbody-request.json"
    When  user sends a delete category request to "/categories/{id}" with ID "16" where id is the value of category ID
    Then  deleted category status code is "200"