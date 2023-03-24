Feature: Get a Donation
  In order to get a Take
  As a propagator

  Background:
    Given There is a registered propagator with username "propagator"

  Scenario: Get an existing Take
    Given I login as "propagator" with password "password"
    And There is a take created with amount 10, weight 5 and location "Lleida"
    When I try to get the take
    Then The response code is 200

  Scenario: Get a Take that doesn't exist
    Given I login as "propagator" with password "password"
    And There is no take created with id 999
    And I try to get the take
    Then The response code is 404