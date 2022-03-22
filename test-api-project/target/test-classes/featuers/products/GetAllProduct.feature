@Regression
@GetAllProducts
Feature: Get All Products

  @HappyScenario
  Scenario: get all available product
    Given User send a get request to "/products"
    Then status code is "200"
    And response body matches "scenarios/getallproducts/response-body.json" in Api contract