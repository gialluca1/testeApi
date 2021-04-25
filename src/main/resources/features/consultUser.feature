@userConsult
@user
Feature: Consult user

  Background:
    * url BaseURL

  Scenario: get all users and then get the first user by id
    Given path 'users'
    When method get
    Then status 200

    * def first = response[0]

    Given path 'users', first.id
    When method get
    Then status 200
    And match response == read('classpath:dataMatch/client1.json')

