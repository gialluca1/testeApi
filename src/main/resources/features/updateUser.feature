@userUpdate
@user
Feature: Update user

  Background:
    * url BaseURL

  Scenario: Update a user
    Given path 'users/' + 1
    And request read('classpath:dataParam/updateClient.json')
    When method put
    Then status 200
    And match response == read('classpath:dataMatch/updateClient.json')

