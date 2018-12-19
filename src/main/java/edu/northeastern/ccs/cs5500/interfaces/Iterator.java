package edu.northeastern.ccs.cs5500.interfaces;

import java.util.List;

/**
 * An interface Iterator to mimic the functionality of a default iterator
 * @author Srijit
 */
public interface Iterator<T> {

    /**
     * Get the card count
     * @return int : card count
     */
    int getCardCount();

    /**
     * Method to decrease the card count
     */
    void decreaseCardCount();

    /**
     * Set to first
     */
    void first();

    /**
     * Advance to next
     */
    void next();

    /**
     * Check if list is fully iterated through
     * @return boolean : true if list if fully iterated through
     */
    boolean isDone();

    /**
     * Get the current object
     * @return T : current object of kind T
     */
    T current();
}


