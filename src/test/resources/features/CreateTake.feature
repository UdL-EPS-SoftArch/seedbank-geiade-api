Feature: Create Take
  In order to be able to take batches
  As a Propagator
  I want to be able to take

  Background:
    Given There is a registered user with username "propagator" and password "password" and email "propagator@sample.app"

  Scenario: Create a new take
    Given I can login with username "propagator" and password "password"
    And The response code is 200
    And There is a valid Propagator
    And There is a valid Take
    When I create a new valid Take
    Then The response code is 201
    And There is 1 take created

  Scenario: Create a new Take without a propagator
    Given I can login with username "propagator" and password "password"
    And The response code is 200
    And There is a valid Take
    But There is no Propagator
    When I create a new take without a propagator
    Then The response code is 400
    And There is 0 take created





