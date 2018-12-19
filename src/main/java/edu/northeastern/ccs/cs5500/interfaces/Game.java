package edu.northeastern.ccs.cs5500.interfaces;

/**
 * An interface to initiate a game of cards based on the deck type
 * @author Srijit
 */

public interface Game {
    /**
     * Create a deck of cards based on the deck type.
     * @param deckType : type of deck
     */
    void createDeck(String deckType);

    /**
     * Create a deck of cards based on the deck type and number of decks
     * @param deckType : type of deck
     * @param numberOfDecks : number of decks
     */
    void createDeck(String deckType, int numberOfDecks);

    /**
     * Set the number of hands
     * @param numberOfHands : number of hands
     */
    void setNumberOfHands(int numberOfHands);

    /**
     * Deal the deck of cards
     */
    void deal();

    /**
     * Get the instance of Deck class
     * @return Deck : a deck of cards
     */
    Deck getDeck();

    /**
     * Get the hands created from the deck
     * @return NewHand[] : an array of hands
     */
    NewHand[] getHands();

    /**
     * Start playing
     */
    void play();
}
