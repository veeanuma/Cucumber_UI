Feature: User Login
  Description: This feature will test the login functionality of the application

  @login @visualAI
  Scenario Outline: Successful Login with valid credentials
    Given open browser
    When User launch Login page
    Then User enters "<username>" and "<password>" valid credentials
    And User is redirected to the Dashboard
    And User closes the browser

    Examples:
      | username               | password  |
      | gogowad347@erapk.com  | Sreenu80@ |
      | jereji7472@doishy.com | Sreenu80@ |
      | mimoyac655@doishy.com | Sreenu80@ |
      | gogowad347@erapk.com  | Sreenu80@ |
