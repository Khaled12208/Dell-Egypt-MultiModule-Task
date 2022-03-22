@Regression
@GetProductByID
Feature: Get One Product by ID

@HappyScenario
Scenario Outline: User try to get a product with valid product id
Given user send a request to get "/products/{id}" with valid "<id>"
Then Positive status code is "200"
And  get product response body is "<response>"
Examples:
| id      | response                                               |
#Valid product ID and response body
| 185230  | scenarios/getproductbyid/response-body_Id_185230.json  |

@NegativeScenarios
Scenario Outline: User try to get product with invalid
Given user send a request to get "/products/{id}" with invalid "<id>"
Then Negative status code is "404"
And there is no "<record>" found for this id
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