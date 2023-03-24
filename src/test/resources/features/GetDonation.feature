Feature: Get a Donation
  In order to get a Donation
  As a donor

  Background:
    Given There is a registered donor with username "userdonor"

  Scenario: Get an existing Donation
    Given I login as "userdonor" with password "password"
    And There is a donation created with amount 10, weight 5 and location "Barcelona"
    And I try to get the donation
    Then The response code is 200

  Scenario: Get a Donation that doesn't exist
    Given I login as "userdonor" with password "password"
    And There is no donation created with id 999
    And I try to get the donation
    Then The response code is 404