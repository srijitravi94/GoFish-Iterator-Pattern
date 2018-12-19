package edu.northeastern.ccs.cs5500;

import edu.northeastern.ccs.cs5500.classes.*;
import edu.northeastern.ccs.cs5500.enums.RankValues;
import edu.northeastern.ccs.cs5500.enums.SuitValues;
import edu.northeastern.ccs.cs5500.interfaces.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static sun.audio.AudioPlayer.player;

/**
 * Class that consists of unit tests to test the game interfaces and classes
 * @author Srijit
 */
public class GameTests {

    private String RANK_NAME_TWO = RankValues.TWO.toString();
    private String RANK_NAME_THREE = RankValues.THREE.toString();
    private String RANK_NAME_FOUR = RankValues.FOUR.toString();
    private String RANK_NAME_FIVE = RankValues.FIVE.toString();
    private String RANK_NAME_SIX = RankValues.SIX.toString();
    private String RANK_NAME_SEVEN = RankValues.SEVEN.toString();
    private String RANK_NAME_EIGHT = RankValues.EIGHT.toString();
    private String RANK_NAME_NINE = RankValues.NINE.toString();
    private String RANK_NAME_TEN = RankValues.TEN.toString();
    private String RANK_NAME_JACK = RankValues.JACK.toString();
    private String RANK_NAME_QUEEN = RankValues.QUEEN.toString();
    private String RANK_NAME_KING = RankValues.KING.toString();
    private String RANK_NAME_ACE = RankValues.ACE.toString();

    private int RANK_PIPS_TWO = RankValues.TWO.getValue();
    private int RANK_PIPS_THREE = RankValues.THREE.getValue();
    private int RANK_PIPS_FOUR = RankValues.FOUR.getValue();
    private int RANK_PIPS_FIVE = RankValues.FIVE.getValue();
    private int RANK_PIPS_SIX = RankValues.SIX.getValue();
    private int RANK_PIPS_SEVEN = RankValues.SEVEN.getValue();
    private int RANK_PIPS_EIGHT = RankValues.EIGHT.getValue();
    private int RANK_PIPS_NINE = RankValues.NINE.getValue();
    private int RANK_PIPS_TEN = RankValues.TEN.getValue();
    private int RANK_PIPS_JACK = RankValues.JACK.getValue();
    private int RANK_PIPS_QUEEN = RankValues.QUEEN.getValue();
    private int RANK_PIPS_KING = RankValues.KING.getValue();
    private int RANK_PIPS_ACE = RankValues.ACE.getValue();


    private String SUIT_NAME_CLUBS = SuitValues.CLUBS.toString();
    private String SUIT_NAME_DIAMONDS = SuitValues.DIAMONDS.toString();
    private String SUIT_NAME_HEARTS = SuitValues.HEARTS.toString();
    private String SUIT_NAME_SPADES = SuitValues.SPADES.toString();

    private char SUIT_SYMBOL_CLUBS = SuitValues.CLUBS.toString().charAt(0);
    private char SUIT_SYMBOL_DIAMONDS = SuitValues.DIAMONDS.toString().charAt(0);;
    private char SUIT_SYMBOL_HEARTS = SuitValues.HEARTS.toString().charAt(0);
    private char SUIT_SYMBOL_SPADES = SuitValues.SPADES.toString().charAt(0);

    private String STANDARD = "Standard";
    private String PINOCHLE = "Pinochle";
    private String EUCHRE = "Euchre";
    private String VEGAS = "Vegas";

    private int OFFICIAL_SIZE_STANDARD = 52;
    private int OFFICIAL_SIZE_PINOCHLE = 48;
    private int OFFICIAL_SIZE_EUCHRE = 24;

    private String SORT_BY_RANK = "byRank";
    private String SORT_BY_SUIT = "bySuit";
    private String SORT_BY_BOTH = "both";

    private int ONE_NUMBER_OF_HANDS = 1;
    private int TWO_NUMBER_OF_HANDS = 2;
    private int THREE_NUMBER_OF_HANDS = 3;
    private int TEN_NUMBER_OF_HANDS = 10;
    private int HUNDRED_NUMBER_OF_HANDS = 100;

    private int NUMBER_OF_CARDS_PER_HAND = 7;

    /**
     * A similar list pertains to the game as well.
     */
    @Test
    public void testCreateDeckInGame() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);

        // Test for createDeck() function
        game.createDeck(STANDARD);
        assertEquals(OFFICIAL_SIZE_STANDARD, game.getDeck().officialSize());

        game.createDeck(EUCHRE);
        assertEquals(OFFICIAL_SIZE_EUCHRE, game.getDeck().officialSize());

        game.createDeck(PINOCHLE);
        assertEquals(OFFICIAL_SIZE_PINOCHLE, game.getDeck().officialSize());

        game.createDeck(VEGAS);
        assertEquals(6*OFFICIAL_SIZE_STANDARD, game.getDeck().officialSize());

        game.createDeck(VEGAS, 8);
        assertEquals(8*OFFICIAL_SIZE_STANDARD, game.getDeck().officialSize());

    }

    /**
     * Check card length of the remaining card list after dealing in a standard deck
     */
    @Test
    public void testCardLengthAfterDealInStandard() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(STANDARD);
        game.setNumberOfHands(TWO_NUMBER_OF_HANDS);
        int officialSize = game.getDeck().officialSize();

        assertEquals(officialSize, OFFICIAL_SIZE_STANDARD);

        game.deal();

        int currentSize = game.getDeck().getCardList().size();

        assertEquals(officialSize - (TWO_NUMBER_OF_HANDS*NUMBER_OF_CARDS_PER_HAND), currentSize);
    }

    /**
     * Check card length of the remaining card list after dealing in a euchre deck
     */
    @Test
    public void testCardLengthAfterDealInEuchre() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(EUCHRE);
        game.setNumberOfHands(TWO_NUMBER_OF_HANDS);

        int officialSize = game.getDeck().officialSize();

        assertEquals(officialSize, OFFICIAL_SIZE_EUCHRE);

        game.deal();

        int currentSize = game.getDeck().getCardList().size();

        assertEquals(officialSize - (TWO_NUMBER_OF_HANDS*NUMBER_OF_CARDS_PER_HAND), currentSize);
    }

    /**
     * Check card length of the remaining card list after dealing in a euchre deck
     */
    @Test
    public void testCardLengthAfterDealInPinochle() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(PINOCHLE);
        game.setNumberOfHands(TWO_NUMBER_OF_HANDS);

        int officialSize = game.getDeck().officialSize();

        assertEquals(officialSize, OFFICIAL_SIZE_PINOCHLE);

        game.deal();

        int currentSize = game.getDeck().getCardList().size();

        assertEquals(officialSize - (TWO_NUMBER_OF_HANDS*NUMBER_OF_CARDS_PER_HAND), currentSize);
    }

    /**
     * Check card length of the remaining card list after dealing in a euchre deck
     */
    @Test
    public void testCardLengthAfterDealInVegas() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(VEGAS);
        game.setNumberOfHands(TWO_NUMBER_OF_HANDS);

        int officialSize = game.getDeck().officialSize();

        assertEquals(officialSize, 6 * OFFICIAL_SIZE_STANDARD);

        game.deal();

        int currentSize = game.getDeck().getCardList().size();

        assertEquals(officialSize - (TWO_NUMBER_OF_HANDS * NUMBER_OF_CARDS_PER_HAND), currentSize);
    }

    /**
     * Check card length of the remaining card list after dealing in a Pinochle deck
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCardLengthAfterDealInVegasException() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(VEGAS, 8);
        game.setNumberOfHands(HUNDRED_NUMBER_OF_HANDS);

        int officialSize = game.getDeck().officialSize();
        assertEquals(officialSize, 8*OFFICIAL_SIZE_STANDARD);

        game.deal();

        int currentSize = game.getDeck().getCardList().size();

        assertEquals(officialSize - (HUNDRED_NUMBER_OF_HANDS * NUMBER_OF_CARDS_PER_HAND), currentSize);
    }

    /**
     * Check card length after initializing the game
     */
    @Test
    public void testCardLengthAfterInitializing() {
        GameFactory factory = new GameFactory();
        GameClass game = new GameClass(factory);
        game.initializeGoFishGame();

        assertTrue(game.getDeck().getCardList().size() == OFFICIAL_SIZE_STANDARD);
    }

    /**
     * Check take one card from deck method
     */
    @Test
    public void testTakeOneCardFromDeck(){
        GameFactory factory = new GameFactory();
        GameClass game = new GameClass(factory);
        game.initializeGoFishGame();

        Rank rank = new RankClass(RANK_NAME_TWO, RANK_PIPS_TWO);
        Suit suit = new SuitClass(SUIT_NAME_DIAMONDS, SUIT_SYMBOL_DIAMONDS);
        Card card = new CardClass(rank, suit);

        Rank rank1 = new RankClass(RANK_NAME_THREE, RANK_PIPS_THREE);
        Suit suit1 = new SuitClass(SUIT_NAME_DIAMONDS, SUIT_SYMBOL_DIAMONDS);
        Card card1 = new CardClass(rank1, suit1);

        NewHand player = new NewHandClass();
        NewHand dealer = new NewHandClass();

        player.accept(card1);
        dealer.accept(card);
        int beforeSize = player.showCards().size();
        game.takeOneCardFromDeck(player);

        assertEquals(beforeSize+1, player.showCards().size());

    }

    /**
     * Check remove is book
     */
    @Test
    public void testRemoveBook() {
        GameFactory factory = new GameFactory();
        GameClass game = new GameClass(factory);
        game.initializeGoFishGame();

        Rank rank = new RankClass(RANK_NAME_TWO, RANK_PIPS_TWO);
        Suit suit = new SuitClass(SUIT_NAME_DIAMONDS, SUIT_SYMBOL_DIAMONDS);
        Card card = new CardClass(rank, suit);

        NewHand player = new NewHandClass();

        player.accept(card);
        player.accept(card);
        player.accept(card);
        player.accept(card);

        game.removeBooks(player);

        assertEquals(0, player.showCards().size());

    }

    /**
     * Check get requested cards
     */
    @Test
    public void testRequestedCards() {
        GameFactory factory = new GameFactory();
        GameClass game = new GameClass(factory);
        game.initializeGoFishGame();

        Rank rank = new RankClass(RANK_NAME_TWO, RANK_PIPS_TWO);
        Suit suit = new SuitClass(SUIT_NAME_DIAMONDS, SUIT_SYMBOL_DIAMONDS);
        Card card = new CardClass(rank, suit);

        NewHand player = new NewHandClass();

        player.accept(card);
        player.accept(card);
        player.accept(card);
        player.accept(card);

        List<Card> cardList = game.getRequestedCards(player, rank);

        assertEquals(4, cardList.size());

    }

    /**
     * Check get winner
     */
    @Test
    public void testGetWinner(){
        GameFactory factory = new GameFactory();
        GameClass game = new GameClass(factory);
        game.initializeGoFishGame();

        NewHand player1 = new NewHandClass();
        NewHand player2 = new NewHandClass();

        player1.setPoints(10);
        player2.setPoints(3);

        NewHand[] players = new NewHandClass[2];
        players[0] = player1;
        players[1] = player2;

        assertEquals(0, game.getWinner(players));
    }

    /**
     * Check get winner
     */
    @Test
    public void testPlay(){
        GameFactory factory = new GameFactory();
        GameClass game = new GameClass(factory);
        game.play();

        NewHand[] players = game.getPlayers();

        assertTrue(game.getWinner(players) >= 0 && game.getWinner(players) <= 1);
    }
}
