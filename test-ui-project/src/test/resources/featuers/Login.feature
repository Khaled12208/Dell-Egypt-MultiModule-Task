@Regression
@login
Feature:Login

  @HappyScenario
  Scenario Outline:login with valid email and password
    Given  A user in the login page
    When   user enter email "<email>"  and password "<password>"
    And    click on the login button
    Then   user navigated to home page
    Examples:
      | email         | password     |
      | standard_user | secret_sauce |

  @NegativeScenarios
  Scenario Outline:login invalid credentials
    Given  A user in the login page
    When   user enter login "<case>"  from  "<dataFile>"
    And    click on the login button
    Then   system displays an error message says "<message>"
    Examples:
      | case                                   | dataFile                     | message                                                                   |
      | invalid username and valid password    | testdata/LoginData.xlsx      | Epic sadface: Username and password do not match any user in this service |
      | valid username and invalid password    | testdata/LoginData.xlsx      | Epic sadface: Username and password do not match any user in this service |
      | invalid username and password          | testdata/LoginData.xlsx      | Epic sadface: Username and password do not match any user in this service |
      | Missing password                       | testdata/LoginData.xlsx      | Epic sadface: Password is required                                        |

