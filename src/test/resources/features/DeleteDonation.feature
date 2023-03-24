Feature: Delete Donation
  In order to delete donations
  As a donor

  Background:
    Given There is a registered donor with username "userdonor"

  Scenario: Delete a Donation when already authenticated
    Given I login as "userdonor" with password "password"
    And There is a donation created with amount 10, weight 5 and location "Barcelona"
    When I delete the donation
    Then The response code is 204
    And I try to get the donation
    And The response code is 404

  Scenario: Delete a Donation without authentication
    Given I'm not logged in
    And There is a donation created with amount 10, weight 5 and location "Lleida"
    When I delete the donation
    Then The response code is 401