package edu.northeastern.ccs.cs5500.classes;

import edu.northeastern.ccs.cs5500.interfaces.Card;
import edu.northeastern.ccs.cs5500.interfaces.Standard;

import java.util.ArrayList;
import java.util.List;

public class StandardDeckClass extends DeckClass implements Standard {

    private List<Card> standardDeckCardList = new ArrayList<>();
    private int NUMBER_OF_STANDARD_DECKS = 1;
    private String STANDARD = "Standard";

    /**
     * Constructor to create the standard deck card list
     */
    StandardDeckClass() {
        createCards(STANDARD, NUMBER_OF_STANDARD_DECKS);
        this.standardDeckCardList = getCardList();
    }


    /**
     * Method to get the standard deck card list
     * @return List : a list of cards
     */
    @Override
    public List<Card> getStandardDeckCardList(){
        return standardDeckCardList;
    }
}
