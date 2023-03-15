Feature: Delete Request
  In order to delete requests
  As a propagator

  Background:
    Given There is a Request created with Id "1"

  Scenario: Delete a Request when already authenticated
    Given I login as "propagator" with password "password"
    When I delete request with id
    Then The response code is 201

