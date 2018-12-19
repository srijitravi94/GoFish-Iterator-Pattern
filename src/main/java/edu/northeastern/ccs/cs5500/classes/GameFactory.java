package edu.northeastern.ccs.cs5500.classes;

/**
 * A Factory of methods to create a Deck and Hands based on the game.
 * @author Srijit
 */
public class GameFactory {

    /**
     * Method to return an instance of standard deck class
     * @return StandardDeckClass : an instance of standard deck class
     */
    public StandardDeckClass makeStandardDeck(){ return new StandardDeckClass();}

    /**
     * Method to return an instance of pinochle deck class
     * @return PinochleDeckClass : an instance of pinochle deck class
     */
    public PinochleDeckClass makePinochleDeck(){ return new PinochleDeckClass();}

    /**
     * Method to return an instance of euchre deck class
     * @return EuchreDeckClass : an instance of euchre deck class
     */
    public EuchreDeckClass makeEuchreDeck(){ return new EuchreDeckClass();}

    /**
     * Method to return an instance of vegas deck class
     * @param numberOfDecks : number of decks to be created
     * @return VegasDeckClass : an instance of vegas deck class
     */
    public VegasDeckClass makeVegasDeck(int numberOfDecks){ return new VegasDeckClass(numberOfDecks);}

    /**
     * Method to initiate the hands array
     * @param numberOfHands : number of hands to be dealt
     * @return Hands[] : an array of objects of type Hand
     */
    public NewHandClass[] makeHands(int numberOfHands){
        NewHandClass[] hands = new NewHandClass[numberOfHands];
        for(int i=0; i<numberOfHands; i++) {
            hands[i] = new NewHandClass();
        }
        return hands;

    }

}
