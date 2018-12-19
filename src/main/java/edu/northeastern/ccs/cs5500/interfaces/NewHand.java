package edu.northeastern.ccs.cs5500.interfaces;

/**
 * An interface NewHand that extends the Hand interface that consists of additional methods that uses the iterator interface
 * @author Srijit
 */
public interface NewHand extends Hand {

    /**
     * Check whether a hand has the given card
     * @param card : a card
     * @return Boolean : true if the given card is in the hand
     */
    @Override
    Boolean hasCard(Card card);

    /**
     * Find number of occurrences of a particular card in a hand
     * @param cardToFind : a card
     * @return int : number of occurrences of the given card
     */
    int occurrencesInHand(Card cardToFind);

    /**
     * Find number of occurrences of a particular rank in a hand
     * @param rankToFind : a rank
     * @return int : number of occurrences of the given rank
     */
    int occurrencesInHand(Rank rankToFind);

    /**
     * Method to get a new hand iterator instance
     * @return HandIterator : a hand iterator
     */
    Iterator<Card> iterator();

    /**
     * Get points acquired while playing the game
     * @return int : points accumulated
     */
    int getPoints();

    /**
     * Set points acquired while playing the game
     * @param points : points to set
     */
    void setPoints(int points);

}
