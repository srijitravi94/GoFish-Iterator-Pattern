package edu.northeastern.ccs.cs5500.classes;

import edu.northeastern.ccs.cs5500.interfaces.Card;
import edu.northeastern.ccs.cs5500.interfaces.Rank;
import edu.northeastern.ccs.cs5500.interfaces.Suit;

/**
 * Class Card implements the card interface to get and set Rank and Suit objects of the card.
 * @author Srijit
 */

public class CardClass implements Card {

    private Rank rank;
    private Suit suit;

    /**
     * Constructor to set Rank and Suit objects of the card
     * @param rank : Rank object of the card
     * @param suit : Suit object of the card
     */
    public CardClass(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Method to get the rank object of the card
     * @return Rank : rank object of the card
     */
    @Override
    public Rank getRank() {
        return rank;
    }

    /**
     * Method to get the suit object of the card
     * @return Suit : suit object of the card
     */
    @Override
    public Suit getSuit() {
        return suit;
    }
}
