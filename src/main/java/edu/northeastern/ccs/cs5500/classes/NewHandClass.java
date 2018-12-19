package edu.northeastern.ccs.cs5500.classes;

import edu.northeastern.ccs.cs5500.interfaces.Card;
import edu.northeastern.ccs.cs5500.interfaces.Iterator;
import edu.northeastern.ccs.cs5500.interfaces.NewHand;
import edu.northeastern.ccs.cs5500.interfaces.Rank;

import java.util.List;

/**
 * A Class that extends HandClass and implements interface newHand that consists of set of methods to help play the game
 * @author Srijit
 */
public class NewHandClass extends HandClass implements NewHand {

    private int points;

    /**
     * Constructor to initialize points for a player
     */
    public NewHandClass(){
        this.points = 0;
    }

    /**
     * Method to get points acquired while playing the game
     * @return int : points accumulated
     */
    public int getPoints() {
        return points;
    }

    /**
     * Method to set points acquired while playing the game
     * @param points : points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Method to check if a given card is present in the hand using the bespoke iterator
     * @param card : a card
     * @return Boolean : true if the given card is in the hand
     */
    @Override
    public Boolean hasCard(Card card) {
        Iterator<Card> cardIterator = iterator();
        for(cardIterator.first(); !cardIterator.isDone(); cardIterator.next()){
            if(checkIfCardsAreEqual(card, cardIterator.current())){
                return true;
            }
        }
        return false;
    }

    /**
     * Method to find number of occurrences of a particular card in a hand
     * @param cardToFind : a card
     * @return int : number of occurrences of the given card
     */
    @Override
    public int occurrencesInHand(Card cardToFind) {
        int cardOccurrences = 0;
        Iterator<Card> cardIterator = iterator();
        for(cardIterator.first(); !cardIterator.isDone(); cardIterator.next()){
            if(checkIfCardsAreEqual(cardToFind, cardIterator.current())){
                cardOccurrences++;
            }
        }
        return cardOccurrences;
    }

    /**
     * Method to find number of occurrences of a particular rank in a hand
     * @param rankToFind : a rank
     * @return int : number of occurrences of the given rank
     */
    @Override
    public int occurrencesInHand(Rank rankToFind) {
        int rankOccurrences = 0;
        Iterator<Card> cardIterator = iterator();
        for(cardIterator.first(); !cardIterator.isDone(); cardIterator.next()){
            if(checkIfRanksAreEqual(rankToFind, cardIterator.current().getRank())){
                rankOccurrences++;
            }
        }
        return rankOccurrences;
    }

    /**
     * Method to get a new hand iterator instance
     * @return HandIterator : a hand iterator
     */
    public Iterator<Card> iterator(){
        return new HandIterator(this);
    }

    /**
     * A Class that implements Iterator interface that consists of set of methods to iterate through the hand
     */
    private class HandIterator implements Iterator<Card> {

        private List<Card> cardList;
        private int cardCount;

        /**
         * Constructor to set the card list and initiate the card count
         * @param newHand : hand for which iterator function should be implemented
         */
        HandIterator(NewHandClass newHand) {
            cardList = newHand.showCards();
            cardCount = 0;
        }

        /**
         * Method to decrease the card count
         */
        @Override
        public void decreaseCardCount() {
            cardCount--;
        }

        /**
         * Method to get the card count
         * @return int : card count
         */
        @Override
        public int getCardCount() {
            return cardCount;
        }

        /**
         * Method to set to first
         */
        @Override
        public void first() {
            cardCount = 0;
        }

        /**
         * Method to advance to next
         */
        @Override
        public void next() {
            cardCount++;
        }

        /**
         * Method to check if list is fully iterated through
         * @return boolean : true if list if fully iterated through
         */
        @Override
        public boolean isDone() {
            return cardCount == cardList.size();
        }

        /**
         * Get the current object
         * @return Card : card
         */
        @Override
        public Card current() {
            return cardList.get(cardCount);
        }
    }
}

