@Regression
@DeleteProduct
Feature: Delete a product using the product ID

  @HappyScenario
  Scenario: User will try to delete a product using a valid product ID
    Given A PRE-created product with data "scenarios/deleteproductbyid/valid-fullbody-request.json"
    And   The user wants to delete this product using the ID
    When  user sends a delete request to "/products/{id}" where id is the value of product ID
    Then  deleted product status code is "200"
    And if the user try it to get this product the status code is "404"