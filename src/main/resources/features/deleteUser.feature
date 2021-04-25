@userDelete
@user
Feature: Delete user

  Background:
    * url BaseURL

  Scenario: delete a user
    Given path 'users/' + 1
    When method delete
    Then status 200


