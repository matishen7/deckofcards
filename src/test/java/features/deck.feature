Feature: Implement automation to test the "Deck of Cards" APIs in Java

  Scenario:
  Create a new deck of cards without Jokers
    Given Endpoint to Open a brand new deck of cards
    When I send a request with GET Method to open a brand new deck of cards
    Then I should get a brand new deck of cards
    And Number of remaining cards should be 52

  Scenario:
  Create a new deck of cards with Jokers
    Given Endpoint to Open a brand new deck of cards
    When I send a request with GET Method to open a brand new deck of cards with Jokers
    Then I should get a brand new deck of cards
    And Number of remaining cards should be 54

  Scenario:
  Draw a card from a deck
    Given Endpoint to Open a brand new deck of cards
    And I send a request with GET Method to open a brand new deck of cards with Jokers
    When I send a request to draw a card from a newly opened deck with 2
    Then I should get a deck
    And Number of remaining cards should be 52

  Scenario:
  Draw a card from a deck
    Given Endpoint to Open a brand new deck of cards
    And I send a request with GET Method to open a brand new deck of cards without Jokers
    When I send a request to draw a card from a newly opened deck with 2
    Then I should get a deck
    And Number of remaining cards should be 50