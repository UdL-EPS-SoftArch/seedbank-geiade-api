Feature: Register Propagator
  In order to use the app
  As a propagator
  I want to register myself and get a propagator account

  Scenario: Register new propagator
    Given There is no registered propagator with username "propagator1"
    And I'm not logged in
    When I register a new propagator with username "propagator1", email "propagator1@sample.app" and password "password"
    Then The response code is 201
    And It has been created a propagator with username "propagator1" and email "propagator1@sample.app", the password is not returned
    And I can login with username "propagator1" and password "password"

  Scenario: Register existing propagator
    Given There is a registered propagator with username "propagator2" and password "existing" and email "propagator2@sample.app"
    And I'm not logged in
    When I register a new propagator with username "propagator2", email "propagator2@sample.app" and password "newpassword"
    Then The response code is 409
    And I cannot login with username "propagator2" and password "newpassword"

  Scenario: Register propagator when already authenticated
    Given There is no registered propagator with username "propagator4"
    And I'm not logged in
    When I register a new propagator with username "propagator4", email "propagator4@sample.app" and password "password"
    Given I can login with username "propagator4" and password "password"
    Then The response code is 200
    When I register a new propagator with username "propagator3", email "propagator3@sample.app" and password "password"
    Then The response code is 403
    And It has not been created a propagator with username "propagator3"

  Scenario: Register new propagator with empty password
    And I'm not logged in
    When I register a new propagator with username "propagator4", email "propagator4@sample.app" and password ""
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a propagator with username "propagator4"

  Scenario: Register new propagator with empty email
    And I'm not logged in
    When I register a new propagator with username "propagator4", email "" and password "password"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a propagator with username "propagator4"

  Scenario: Register new propagator with invalid email
    And I'm not logged in
    When I register a new propagator with username "propagator4", email "prop.com" and password "password"
    Then The response code is 400
    And The error message is "must be a well-formed email address"
    And It has not been created a propagator with username "propagator4"

  Scenario: Register new propagator with password shorter than 8 characters
    And I'm not logged in
    When I register a new propagator with username "propagator4", email "propagator4@sample.app" and password "pass"
    Then The response code is 400
    And The error message is "length must be between 8 and 256"
    And It has not been created a propagator with username "propagator4"

  Scenario: Register new propagator with an existing email
    Given There is a registered propagator with username "propagator" and password "password" and email "propagator@sample.app"
    And I'm not logged in
    When I register a new propagator with username "propagator5", email "propagator@sample.app" and password "password2"
    Then The response code is 409
    And I can login with username "propagator" and password "password"