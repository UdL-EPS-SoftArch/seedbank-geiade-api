Feature: Register New Donation
  In order to donate
  As a donor
  I want to register new donation

  Scenario: Register new donation with a donor registered
    Given There is a donor registered with the username "Joan"
    When I create a new donation
    And I assign a donor to the donation
    Then The donor of the donation is "Joan"
