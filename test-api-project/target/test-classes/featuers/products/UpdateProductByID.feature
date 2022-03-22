@Regression
@UpdateProduct
Feature: User Wants to update a product data

  @HappyScenario
  Scenario Outline: User try to update the product with valid data
    Given user wants to update a product using patch "/products/{id}" with id "<id>"
    And   update product body content type is "application/json"
    When  User send a patch request to update with "<requestBody>"
    Then  update product response status code is "200"
    And   update product response header contains "ETag" header
    And   update product response body matches "<responseBody>"
    Examples:
     | id     | requestBody                                                        | responseBody                                                       |
                #Valid product ID and Full body update (Mandatory and un mandatory fields)
     | 312290 | scenarios/updateproduct/UpdateProduct_AllFields-request.json       | scenarios/updateproduct/UpdateProduct_AllFields-response.json      |
           #Valid product ID and partial body update (Mandatory Only)
     | 312290 | scenarios/updateproduct/UpdateProduct_MandatoryFields-request.json | scenarios/updateproduct/UpdateProduct_MandatoryFields-response.json|

  @NegativeScenarios
  Scenario: User try to update the product with invalid data Missing product mandatory items
    Given user wants to update a product using patch "/products/{id}" with id "312290"
    When  User send a patch request to update with "scenarios/updateproduct/UpdateProduct_MissingName-request.json"
    Then  update product response status code is "400"
    And   update product response body matches "scenarios/updateproduct/UpdateProduct-Error-response.json"
