Feature: Register Admin
  In order to use the app
  As an admin
  I want to register myself and get an account

  Scenario: Register new admin
    Given There is no registered admin with username "admin"
    And I'm not logged in
    When I register a new admin with username "admin", email "admin@sample.app" and password "password"
    Then The response code is 201
    And It has been created a admin with username "admin" and email "admin@sample.app", the password is not returned

  Scenario: Register admin existing username
    Given There is a registered admin with username "admin" and password "existing" and email "admin@sample.app"
    And I'm not logged in
    When I register a new admin with username "admin", email "admin@sample.app" and password "newpassword"
    Then The response code is 409
    And I cannot login admin with username "admin" and password "newpassword"

  Scenario: Register admin when already authenticated
    Given I login as "demo" with password "password"
    When I register a new admin with username "admin", email "admin@sample.app" and password "password"
    Then The response code is 403
    And It has not been created a admin with username "admin"

  Scenario: Register admin with empty password
    Given I'm not logged in
    When I register a new admin with username "admin", email "admin@sample.app" and password ""
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a admin with username "admin"

  Scenario: Register admin with empty email
    Given I'm not logged in
    When I register a new admin with username "admin", email "" and password "password"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a admin with username "admin"

  Scenario: Register admin with invalid email
    Given I'm not logged in
    When I register a new admin with username "admin", email "adminsample.app" and password "password"
    Then The response code is 400
    And The error message is "must be a well-formed email address"
    And It has not been created a admin with username "admin"

  Scenario: Register admin with password shorter than 8 characters
    Given I'm not logged in
    When I register a new admin with username "admin", email "admin@sample.app" and password "pass"
    Then The response code is 400
    And The error message is "length must be between 8 and 256"
    And It has not been created a admin with username "admin"
