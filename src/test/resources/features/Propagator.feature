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

