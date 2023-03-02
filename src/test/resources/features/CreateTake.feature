Feature: Create Take
  In order to be able to take batches
  As a Propagator
  I want to be able to take

  Background:
    Given There is a registered user with username "propagator" and password "password" and email "propagator@sample.app"

  Scenario: There is a take
    Given I login as "propagator" with password "password"
    And The response code is 200
    When a propagator
    Then The response code is 201

  Scenario: Any batch has been taken by a propagator
    Given I login as "propagator" with password "password"
    And any batch has been taken
    When I take "0" batches
    Then The response code is 201

  Scenario: Any batch has been taken by a propagator
    Given I login as "propagator" with password "password"
    And any batch has been taken
    When I take "0" batches
    Then The response code is 201

