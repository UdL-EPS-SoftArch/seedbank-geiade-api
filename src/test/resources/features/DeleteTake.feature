Feature: Delete Take
  In order to be able to delete Take
  As a Propagator
  I want to be able to delete a Take

  Background:
    Given There is a registered propagator with username "propagator"

  Scenario: Delete a Take with user not logged in
    Given I'm not logged in
    And There is a take created with amount 10, weight 5 and location "Lleida"
    When I delete a Take
    Then The response code is 401

  Scenario: Delete a Take with user logged in
    Given I login as "propagator" with password "password"
    And There is a take created with amount 10, weight 5 and location "Lleida"
    When I delete a Take
    Then The response code is 204
    And I try to get the take
    And The response code is 401


