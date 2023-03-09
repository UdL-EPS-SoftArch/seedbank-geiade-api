Feature: Create Request
  In order to create requests
  As a propagator

  Background:
    Given There is a registered propagator with username "propagator"

  Scenario: Create a new request when already authenticated
    Given I login as "propagator" with password "password"
    When I create a new request with propagator with username "propagator"
    Then The response code is 201

  Scenario: Create