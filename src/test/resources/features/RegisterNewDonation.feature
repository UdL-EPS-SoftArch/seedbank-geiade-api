Feature: Register New Donation
  In order to donate
  As a donor
  I want to register new donation

  Scenario: Register new donation
    When I create a new donation
    Then The status is "Pending"
