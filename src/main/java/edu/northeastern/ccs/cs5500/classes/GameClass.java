package edu.northeastern.ccs.cs5500.classes;

import edu.northeastern.ccs.cs5500.interfaces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A class that implements the Game interface and start playing the card game based on the deck type
 * @author Srijit
 */

public class GameClass implements Game {

    private String STANDARD = "Standard";
    private int NUMBER_OF_CARDS_PER_HAND = 7;
    private int DEFAULT_NUMBER_OF_DECKS = 1;
    private int DEFAULT_NUMBER_OF_DECKS_VEGAS = 6;
    private int NUMBER_OF_HANDS = 2;
    private int STANDARD_DECK_SIZE = 52;
    private int IS_BOOK = 4;
    private int MAX_GAME_LIMIT = 13;

    private List<Card> cardList;
    private int booksAcquired;
    private NewHand[] players;
    private DeckClass deck;
    private GameFactory gameFactory;
    private Random randInt;
    private int numberOfHands;

    /**
     * Constructor to set game factory
     * @param gameFactory : an object of GameFactory
     */
    public GameClass(GameFactory gameFactory){
        this.gameFactory = gameFactory;
        this.players = gameFactory.makeHands(NUMBER_OF_HANDS);
        this.deck = new DeckClass();
        this.randInt = new Random();
        this.cardList = new ArrayList<>();
        this.booksAcquired = 0;
        this.numberOfHands = NUMBER_OF_HANDS;
    }

    /**
     * Method to get the players
     * @return NewHand[] : an array of hands
     */
    public NewHand[] getPlayers() {
        return players;
    }

    /**
     * Method to create deck
     * @param deckType : type of deck
     */
    @Override
    public void createDeck(String deckType) {
        if(deckType.equals("Vegas")) {
            createGame(deckType, DEFAULT_NUMBER_OF_DECKS_VEGAS);
        } else {
            createGame(deckType, DEFAULT_NUMBER_OF_DECKS);
        }
    }

    /**
     * Method to create deck with the number of decks given.
     * @param deckType : type of deck
     * @param numberOfDecks : number of decks
     */
    @Override
    public void createDeck(String deckType, int numberOfDecks) {
        createGame(deckType, numberOfDecks);
    }

    /**
     * Method to set the number of hands
     * @param numberOfHands : number of hands
     */
    @Override
    public void setNumberOfHands(int numberOfHands) {
        if(numberOfHands <= deck.getCardList().size()) {
            this.numberOfHands = numberOfHands;
            players = gameFactory.makeHands(numberOfHands);
        }
    }

    /**
     * Method to deal the deck of cards
     */
    @Override
    public void deal() {
        for(int i = 0; i< NUMBER_OF_CARDS_PER_HAND ; i++){
            for(int j = 0; j< numberOfHands; j++){
                players[j].accept(cardList.get(0));
                cardList.remove(0);
            }
        }
    }

    /**
     * Method to return the instance of Deck class
     * @return DeckClass : a deck of cards
     */
    @Override
    public DeckClass getDeck() {
        return deck;
    }

    /**
     * Method to get the hands created from the deck
     * @return NewHandClass[] : an array of hands
     */
    @Override
    public NewHand[] getHands(){
        return players;
    }

    /**
     * Method to create a game of given deck type and number of decks using game factory
     * @param deckType : type of deck
     * @param numberOfDecks : number of decks
     */
    private void createGame(String deckType, int numberOfDecks){
        switch (deckType){
            case "Standard":
                Standard standardDeck = gameFactory.makeStandardDeck();
                cardList = standardDeck.getStandardDeckCardList();
                break;

            case "Pinochle":
                PinochleDeckClass pinochleDeck = gameFactory.makePinochleDeck();
                cardList = pinochleDeck.getPinochleDeckCardList();
                break;

            case "Euchre":
                EuchreDeckClass euchreDeck = gameFactory.makeEuchreDeck();
                cardList = euchreDeck.getEuchreDeckCardList();
                break;

            case "Vegas":
                VegasDeckClass vegasDeck = gameFactory.makeVegasDeck(numberOfDecks);
                cardList = vegasDeck.getVegasDeckCardList();
                break;

            default:
                break;
        }

        deck.setCardList(cardList);
        deck.setOfficialSize(cardList.size());

    }

    /**
     * Method to start playing the game of GoFish
     */
    @Override
    public void play() {
        initializeGoFishGame();
        deal();
        playGoFish(players[0], players[1]);
    }

    /**
     * Method that initializes the game play of GoFish
     */
    public void initializeGoFishGame(){
        createDeck(STANDARD);
        setPlayerAndDealer();
        int cutPoint = randInt.nextInt(STANDARD_DECK_SIZE-1) + 1;
        getDeck().cut(cutPoint);
    }

    /**
     * Method that sets the player and dealer for the game
     */
    public void setPlayerAndDealer(){
        getDeck().shuffle();
        NewHand[] tempPlayers = new NewHandClass[2];

        for(int i=0; i<NUMBER_OF_HANDS; i++) {
            tempPlayers[i] = new NewHandClass();
            tempPlayers[i].accept(cardList.get(i));
        }
        int dealerId = getIdOfDealer(tempPlayers);
        NewHand dealer = tempPlayers[dealerId];
        NewHand player = dealerId == 0? tempPlayers[1]: tempPlayers[0];

        players[0] = player;
        players[1] = dealer;

        for(int i=0; i<2; i++){
            players[i].showCards().clear();
        }
        getDeck().shuffle();
    }

    /**
     * Method to get the ID of the dealer based on rank
     * @param players : an array of hands
     * @return int : ID of the dealer
     */
    public int getIdOfDealer(NewHand[] players){
        int dealerId = 0;
        if(players[0].showCards().get(0).getRank().getPips() > players[1].showCards().get(0).getRank().getPips()){
            dealerId = 1;
        }
        return dealerId;
    }

    /**
     * Method to play go fish given a player and an opponent
     * @param player : a player hand
     * @param opponent : an opponent hand
     */
    public void playGoFish(NewHand player, NewHand opponent) {

        if(booksAcquired == MAX_GAME_LIMIT || deck.getCardList().isEmpty()) {
            return;
        }

        if(player.showCards().isEmpty()){
            takeOneCardFromDeck(player);
            playGoFish(opponent, player);
        } else {
            int cardFromPlayer = randInt.nextInt(player.showCards().size());
            Rank requestCard = player.showCards().get(cardFromPlayer).getRank();

            if(opponent.hasRank(requestCard) && !opponent.showCards().isEmpty()) {
                player.showCards().addAll(getRequestedCards(opponent, requestCard));
                removeBooks(player);
                playGoFish(player, opponent);
            } else {
                takeOneCardFromDeck(player);
                playGoFish(opponent, player);
            }
        }
    }

    /**
     * Method to take one card from deck
     * @param player : a hand
     */
    public void takeOneCardFromDeck(NewHand player){
        player.accept(cardList.get(0));
        cardList.remove(0);
    }

    /**
     * Method to remove books (a bunch of four cards with same rank) if any
     * @param player : a player hand
     */
    public void removeBooks(NewHand player){
        Iterator<Card> it = player.iterator();
        for(it.first(); !it.isDone(); it.next()){
            if(player.occurrencesInHand(it.current().getRank()) == IS_BOOK){
                booksAcquired++;
                player.setPoints(player.getPoints()+1);
                removeCards(player, it.current().getRank());
                it.decreaseCardCount();
            }
        }
    }

    /**
     * Method to remove cards of a given rank from a given hand
     * @param player : a player hand
     * @param rank : a rank to remove from the hand
     */
    private void removeCards(NewHand player, Rank rank){
        Iterator<Card> removeCardsIterator = player.iterator();
        for(removeCardsIterator.first(); !removeCardsIterator.isDone(); removeCardsIterator.next()){
            if(checkIfRanksAreEqual(removeCardsIterator.current().getRank(), rank)){
                player.showCards().remove(removeCardsIterator.current());
                removeCardsIterator.decreaseCardCount();
            }
        }
    }

    /**
     * Method to check whether two ranks are same
     * @param rank1 : rank to compare
     * @param rank2 : rank to compare
     * @return Boolean : true if two ranks are same
     */
    public boolean checkIfRanksAreEqual(Rank rank1, Rank rank2) {
        Boolean rankNameCheck = rank1.getName().equals(rank2.getName());
        Boolean rankPipsCheck = rank1.getPips() == rank2.getPips();

        return rankNameCheck && rankPipsCheck;
    }

    /**
     * Method to get cards requested by a player from the opponent
     * @param opponent : an opponent hand
     * @param rank : rank of the card requested by the player
     * @return List<Card> : a list of cards of the rank requested by the player
     */
    public List<Card> getRequestedCards(NewHand opponent, Rank rank){
        List<Card> cardsToGive = new ArrayList<>();
        Iterator<Card> it = opponent.iterator();
        for(it.first(); !it.isDone(); it.next()){
            if(checkIfRanksAreEqual(it.current().getRank(), rank)){
                cardsToGive.add(it.current());
                opponent.showCards().remove(it.current());
                it.decreaseCardCount();
            }
        }
        return cardsToGive;
    }

    /**
     * Method to get the ID of the winning player
     * @return int : ID of the winning player
     */
    public int getWinner(NewHand[] players){
        int winner = 0;
        if(players[0].getPoints() < players[1].getPoints()){
            winner = 1;
        }
        return winner;
    }

}

