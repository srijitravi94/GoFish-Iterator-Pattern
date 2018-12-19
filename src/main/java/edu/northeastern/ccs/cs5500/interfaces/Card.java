package edu.northeastern.ccs.cs5500.interfaces;

/**
 * An interface card to get the rank and suit of the card
 * @author Srijit
 */

public interface Card {
    /**
     * Get the rank object of the card
     * @return Rank : rank object of the card
     */
    Rank getRank();

    /**
     * Get the suit object of the card
     * @return Suit : suit object of the card
     */
    Suit getSuit();
}
