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


