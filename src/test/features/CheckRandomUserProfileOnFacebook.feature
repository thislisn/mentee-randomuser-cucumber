
Feature: Check random user has profile on Facebook

  Scenario: Search in Google for profile of random user on Facebook
    Given get random user name
    And I open google home page
    When I search for random user facebook on google home page