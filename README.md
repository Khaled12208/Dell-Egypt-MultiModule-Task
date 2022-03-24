<div>
<img  alt="traffic" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" width="110" height="35"/>
<img  alt="traffic" src="https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=Selenium&logoColor=white" width="110" height="35"/>
<img  alt="traffic" src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" width="110" height="35"/>
<img  alt="traffic" src="https://qatestlab.com/assets/Uploads/testng1.png" width="110" height="35" />
<img  alt="traffic" src="https://itsadeliverything.com/images/cucumber-logo.png" width="110" height="35"/>
<img  alt="traffic" src="https://i0.wp.com/blog.knoldus.com/wp-content/uploads/2020/05/Rest-assured-logo.png?fit=446%2C113&ssl=1" width="110" height="35" />
</div>

<h1>Test-Multi-Module-Project</h1>

<dl>
    <dt>This project was designed as a part of Dell Egypt assessment for QA automation II</dt>
    <dd>its a multi-module project for automating web UI and API tests and ready to be integrated with jenkins.</dd>
</dl>


## Key facts

1. the framework support parallel execution for web and api tests
2. the framework support test execution reports /test-reports directory
3. the test cases are designed  based on The BDD using cucumber
4. test data are provided using data driven way from external sources
5. the test are set up using external config file browser-config.properties and api-config.properties at /resources
6. the UI test cover 4 features and 12 scenario
7. the API tests cover 6 features and 21 scenario
8. the API tests are designed based on the Swagger specs, and I founded 6 bugs 
9. the UI tests are designed based on exploratory testing and all of them are green


## Pre-requisites

1. Install NodeJs

2. Clone and install the following api project using node npm commands showed in the project ReadMe
    https://github.com/BestBuy/api-playground
3. Make sure the service is working well


## Modules
1. test-api-project: contains API related logic and test suites
2. test-ui-project:  contains UI related logic (Page Object Model , Driver Factory) and test suites
3. common-helpers: contains common libraries for both project (test resources readers , Excel parser and utils )


## Technologies 
1. TestNG
2. Selenium
3. Web Driver Manager
4. REST-ASSURED
5. Cucumber
6. Excel POI
7. Jackson
8. Google Gson
9. Extent-Reports
10. Log4J
11. Simple JSON
12. Maven
13. JAVA
14. Snake Yaml

## Test Output Reports
***All reports contain (Screenshots,Test Coverage and Execution Statistics)***
1. Spark-Report
2. HTML-Report
3. PDF-Report

## How to execute
***Mainly our execution focus on triggering the UI or API project or Both of them 
each project has two execution profiles (Regression (default): contains all test cases, Smoke: contains selected features)***

<h2 style="color:red">The regression profile (default)</h2>

#### * API and UI test: use the following maven command
     mvn install
#### * API tests only: use the following maven command
     mvn install -pl test-api-project -am
#### * UI tests only: use the following maven command
     mvn install -pl test-ui-project -am

<h2 style="color:red">The smoke profile</h2>

#### * Both API and UI tests: use the following maven command
     mvn install -P Smoke
#### * API tests: use the following maven command
    mvn install -pl test-api-project -am -P Smoke
#### * UI tests: use the following maven command
    mvn install -pl test-ui-project -am -P Smoke

## Execution proof:
### API-project
![Alt Text](https://media.giphy.com/media/vkoVdRi0XbjyhI0nDZ/giphy.gif)

### UI-project
![Alt Text](https://media.giphy.com/media/bceydo3ZTPuedokKWO/giphy.gif)



## For any enquires please contact me on
    khaled.farh@gmail.com
    +201226146620