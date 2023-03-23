Feature: Create a Donation
  In order to be able to donate seeds
  As a Donor
  I want to be able to create a donation

  Background:
    Given There is a registered donor with username "userdonor"

  Scenario: Create a new donation successfully
    Given I can login with username "userdonor" and password "password"
    And The response code is 200
    When I create a new valid donation with donor
    Then The response code is 201

  Scenario: Create a new donation with no donor
    Given I can login with username "userdonor" and password "password"
    And The response code is 200
    When I create a donation without donor
    Then The response code is 400

  Scenario: Create a new donation with donor and take
    Given I can login with username "userdonor" and password "password"
    And The response code is 200
    When I create a donation with donor and take
    Then The response code is 201

  Scenario: Create a new donation without attributes
    Given I can login with username "userdonor" and password "password"
    And The response code is 200
    When I create a donation without attributes
    Then The response code is 400



