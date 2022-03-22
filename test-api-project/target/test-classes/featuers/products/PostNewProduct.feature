@Regression
@AddProduct
Feature: User Wants to add a new product

  @HappyScenario
  Scenario: User try to add the product with valid data
    Given user wants to add a product using post "/products"
    And   new product body Content type is "application/json"
    When  User send a post request to update with "scenarios/addnewproduct/valid-fullbody-request.json"
    Then  new product response status code is "201"
    And   new product response header contains "ETag" header
    And   new product response body  should be is empty

  @NegativeScenarios
  Scenario: User try to add the product with valid data and invalid content type
    Given user wants to add a product using post "/products"
    And   new product body Content type is "text/plain"
    When  User send a post request to update with "scenarios/addnewproduct/valid-fullbody-request.json"
    Then  new product response status code is "400"
    And   new product response body should match "scenarios/addnewproduct/error-response.json"


  @NegativeScenarios
  Scenario Outline: User try to add the product with invalid data request body and valid content type
    Given user wants to add a product using post "/products"
    And   new product body Content type is "application/json"
    When  User send a post request to update with "<requestBody>"
    Then  new product response status code is "400"
    And  new product response body should match "scenarios/addnewproduct/error-response.json"
    Examples:
   | requestBody                                                             |
   #Invalid Request Body Missing One of the Mandatory fields
   | scenarios/addnewproduct/invalid-body-missing-mandatoryfilds-request.json|
      #Invalid Request Body entering String value for Integer body param
   | scenarios/addnewproduct/invalid-body-price-string-request.json          |