@DataTypesExample
Feature: Play with data

  Scenario: Collection List test
    Given there is collection of elements element1,element2
    Then source is not empty

  Scenario: CollectionExample the second
    Given there is elements
      | Element  |
      | Element2 |
    Then source is not empty

  Scenario: Map strings test
    Given There is a map of elements
      | key1 | value1 |
      | key2 | value2 |
    Then source map is not empty

  Scenario: Map integer test
    Given There is a map of integer elements
      | key1 | 1 |
      | key2 | 2 |
    Then source integer map is not empty

  Scenario: Map boolean test
    Given There is a map of boolean elements
      | key1 | true |
      | key2 | false |
    Then source boolean map is not empty

    Scenario: Menu test
      Given menuObjectIsAvailable
      |title|isAvailable|subMenuCount|
      |File |true       |5           |
      |Apple|false      |10          |
      |Fish |true       |20          |