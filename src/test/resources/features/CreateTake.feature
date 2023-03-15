Feature: Create Take
  In order to be able to take batches
  As a Propagator
  I want to be able to take

  Background:
    Given There is a registered propagator with username "propagator"

  Scenario: Create a new take successfully.
    Given I can login with username "propagator" and password "password"
    And The response code is 200
    When I create a new valid Take with Propagator
    Then There is 1 take created
    And The response code is 201

  Scenario: Create new Take but not logged in
    Given I'm not logged in
    When I create a new valid Take with Propagator
    Then The response code is 401

  Scenario: Create 5 Takes.
    Given I can login with username "propagator" and password "password"
    And The response code is 200
    When I create 5 takes
    Then There are 5 take created
    And The response code is 201

  Scenario: Create new Take missing some attributes
    Given I can login with username "propagator" and password "password"
    And The response code is 200
    When I create an invalid take
    Then There is 0 take created
    And The response code is 400

  Scenario: Create new Take without a Propagator
    Given I can login with username "propagator" and password "password"
    And The response code is 200
    When I create a take without a Propagator
    Then There is 0 take created
    And The response code is 400

  Scenario: Create new Take without user permissions
    Given I can login with username "donor" and password "password"
    And The response code is 200
    When I create a new valid Take with Donor
    Then There is 0 take created
    And The response code is 403


