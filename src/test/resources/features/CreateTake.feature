Feature: Create Take
  In order to be able to take batches
  As a Propagator
  I want to be able to take

  Background:
    Given There is a registered user with username "propagator" and password "password" and email "propagator@sample.app"

  Scenario: Create a new take
    Given I can login with username "propagator" and password "password"
    #And The response code is 200
    When I create a new valid Take with Propagator with username "propagator"
    Then The response code is 201

    #And There is 1 take created





