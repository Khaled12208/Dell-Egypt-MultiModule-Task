@Regression
@PlaceOrder
Feature: User wants to place order

  @HappyScenario
  Scenario Outline:user try to place order
    Given user is logged in with standard account user name is "standard_user" and password "secret_sauce"
    And   user in the "Products" page
    When  user add item using "<itemsNames>" to the shopping cart
    And   user clicks on the shopping cart icon
    And   click on the checkout button
    And   fill payment information from file "testdata/PaymentInfoData.json"
    And   Click on continue button
    And   the items total price is "<itemsTotalPrice>"
    And   the total price  after tax is "<totalPrice>"
    And   the taxes rate is "8" %
    And   clicks on the finish button
    Then  the order has been created with thanks word "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
    Examples:
      | itemsNames                                 | itemsTotalPrice       |totalPrice   |
      | Sauce Labs Backpack                        |Item Total: $29.99     |Total: $32.39|
      | Sauce Labs Bike Light; Sauce Labs Backpack |Item Total: $39.98     |Total: $43.18|
