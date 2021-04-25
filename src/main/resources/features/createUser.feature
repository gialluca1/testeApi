@userCreate
@user
Feature: Create user

  Background:
    * url BaseURL

  Scenario: create a user
    Given path 'users'
    And request read('classpath:dataParam/newClient.json')
    When method post
    Then status 201
    * def id = response.id
    And match id == 11
    And print id

