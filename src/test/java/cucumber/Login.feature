Feature: Login to a test account

  Background:
    Given User is on Login Page
  Scenario Outline: Valid Login test
    When User enters username <username> and password <password>
    Then User is successfully Logged in

    Examples:
    |username|password|
    |testemail1@gmail.com| Password123!|


