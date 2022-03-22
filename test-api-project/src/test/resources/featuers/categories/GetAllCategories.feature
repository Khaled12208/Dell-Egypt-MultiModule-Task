@Regression
@GetAllCategories
Feature: Get All Categories

  @HappyScenario
  Scenario: get all available Categories
    Given User send a get Categories request to "/categories"
    Then Categories status code is "200"
    And Categories response body matches "scenarios/getallcategories/response-body.json" in Api contract