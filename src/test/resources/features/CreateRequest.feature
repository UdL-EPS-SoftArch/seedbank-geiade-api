Feature: Create Request
  In order to create requests
  As a propagator

  Background:
    Given There is a registered propagator with username "propagator"

  Scenario: Create a new Request when already authenticated
    Given I login as "propagator" with password "password"
    When I create a new request with propagator with username "propagator"
    Then The response code is 201

  Scenario: Create a new Request without authentication
    Given I'm not logged in
    When I create a new request with propagator with username "propagator"
    Then The response code is 401

  Scenario: Create a new Request without Take
    Given I login as "propagator" with password "password"
    When I create a new request without Take with propagator with username "propagator"
    Then The response code is 201

  Scenario: Create a new Request
    Given I login as "propagator" with password "password"
    When I create a new request with propagator with username "propagator"
    Then The response code is 201
    When I create a new request with propagator with username "propagator"
    Then The response code is 400