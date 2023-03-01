Feature: Check Donor's related Donations
  In order to have a control over my donations
  As a donor
  I want see a record of all my existing donations

  Scenario: Donor with no donations
    Given There is a registered Donor with username "donor"
    And I login as "donor" with password "password"
    Then There is no Donations by the Donor with username "donor"