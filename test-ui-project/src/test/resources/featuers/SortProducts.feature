@Regression
@SortProduct
Feature:Sort Products

  @HappyScenario
  Scenario Outline: User wants to sort the products using sorting options
    Given user is logged in with standard account user "standard_user" and password "secret_sauce"
    And   user in the "Products" page
    When  select  one of the sorting type "<sortType>"
    Then  the items should be sorted based the selection "<sortType>"
    Examples:
      | sortType           |
      | Name (A to Z)      |
      | Name (Z to A)      |
      | Price (low to high)|
      | Price (high to low)|