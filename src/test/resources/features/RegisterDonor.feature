Feature: Register Donor
  In order to use the app
  As a donor
  I want to register myself and get an account

  Scenario: Register new donor
    Given There is no registered donor with username "donor"
    And I'm not logged in
    When I register a new donor with username "donor", email "donor@sample.app" and password "password"
    Then The response code is 201
    And It has been created a donor with username "donor" and email "donor@sample.app", the password is not returned
    And I can login donor with username "donor" and password "password"

  Scenario: Register existing username
    Given There is a registered donor with username "donor" and password "existing" and email "donor@sample.app"
    And I'm not logged in
    When I register a new donor with username "donor", email "donor@sample.app" and password "newpassword"
    Then The response code is 409
    And I cannot login donor with username "donor" and password "newpassword"

  Scenario: Register donor when already authenticated
    Given I login as "demo" with password "password"
    When I register a new donor with username "donor", email "donor@sample.app" and password "password"
    Then The response code is 403
    And It has not been created a donor with username "donor"

  Scenario: Register donor with empty password
    Given I'm not logged in
    When I register a new donor with username "donor", email "donor@sample.app" and password ""
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a donor with username "donor"

  Scenario: Register donor with empty email
    Given I'm not logged in
    When I register a new donor with username "donor", email "" and password "password"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a donor with username "donor"

  Scenario: Register donor with invalid email
    Given I'm not logged in
    When I register a new donor with username "donor", email "donorasample.app" and password "password"
    Then The response code is 400
    And The error message is "must be a well-formed email address"
    And It has not been created a donor with username "donor"

  Scenario: Register donor with password shorter than 8 characters
    Given I'm not logged in
    When I register a new donor with username "donor", email "donor@sample.app" and password "pass"
    Then The response code is 400
    And The error message is "length must be between 8 and 256"
    And It has not been created a donor with username "donor"

  Scenario: Register donor with an existing email
    Given There is a registered donor with username "donor" and password "password" and email "donor@sample.app"
    And I'm not logged in
    When I register a new donor with username "donor2", email "donor@sample.app" and password "password2"
    Then The response code is 409
    And I can login donor with username "donor" and password "password"
