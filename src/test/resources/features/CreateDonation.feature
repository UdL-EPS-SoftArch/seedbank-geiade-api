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