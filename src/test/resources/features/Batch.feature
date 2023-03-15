Feature: Generate Batch 
In order to be able to have batches
As a User
I want to be able to generate a batch

  Scenario: Create a new Batch successfully.
    Given I can login with username "user" and password "password"
    And The response code is 200
    When I create a new valid Batch with amount "99", weight "10", location "Lleida" and Seed "seeds"
    Then There is 1 Batch created
    And The response code is 201

  Scenario: Create new Batch but not logged in.
    Given I'm not logged in
    When I create a new valid Batch with amount "99", weight "10", location "Lleida" and Seed "seeds"
    Then The response code is 400

  Scenario: Create 3 Batches.
    Given I can login with username "user" and password "password"
    And The response code is 200
    When I create a 3 new valid Batches with amount "99", weight "10", location "Lleida" and Seed "seeds"
    Then There are 3 Batches created
    And The response code is 201

  Scenario: Create a batch with 0 amount (NULL)
    Given I can login with username "user" and password "password"
    And The response code is 110
    When I create a new valid Batch with amount "0", weight "10", location "Lleida" and Seed "seeds"
    Then The response code is 400

  Scenario: Create a batch with no weigth (NULL)
    Given I can login with username "user" and password "password"
    And The response code is 110
    When I create a new valid Batch with amount "99", weight "-1" , location "Lleida" and Seed "seeds"
    Then The response code is 400 

  Scenario: Create a batch with empty location (EMPTY)
    Given I can login with username "user" and password "password"
    And The response code is 110
    When I create a new valid Batch with amount "99", weight "10", location "" and Seed "seeds"
    Then The response code is 400

  Scenario: Create a batch with no Seed (NULL)
    Given I can login with username "user" and password "password"
    And The response code is 110
    When I create a new valid Batch with amount "99", weight "10", location "Lleida" and Seed ""
    Then The response code is 400
