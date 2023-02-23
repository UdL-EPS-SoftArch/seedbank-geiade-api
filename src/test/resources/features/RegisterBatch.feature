Feature: Register a new Batch
  In order to use the app
  As a user
  I want to create a new batch in order to classify my seeds

  Scenario: Register new batch
    When I register a new batch
    Then Amount is -1