Feature: Delete Request
  In order to delete requests
  As a propagator

  Background:
    Given There is a registered propagator with username "propagator"

  Scenario: Delete a Request when already authenticated
    Given I login as "propagator" with password "password"
    And There is a request created with amount 10, weight 5 and location "Lleida"
    When I delete the request
    Then The response code is 204
    And I try to get the request
    And The response code is 404

  Scenario: Delete a Request without authentication
    Given I'm not logged in
    And There is a request created with amount 10, weight 5 and location "Lleida"
    When I delete the request
    Then The response code is 401
