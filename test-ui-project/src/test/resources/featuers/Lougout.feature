@Regression
@logout
Feature: User logout

  Scenario: user wants to logout
    Given user is logged in with standard account user name "standard_user" and password "secret_sauce"
    When  user click on menu button
    And   click on logout button
    Then  user has been logged out and in login page again
