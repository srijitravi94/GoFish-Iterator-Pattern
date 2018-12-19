package edu.northeastern.ccs.cs5500;

import edu.northeastern.ccs.cs5500.classes.*;
import edu.northeastern.ccs.cs5500.enums.*;
import edu.northeastern.ccs.cs5500.interfaces.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Class that consists of unit tests to test the hand interfaces and classes
 * @author Srijit
 */
public class HandTests {

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

    private String SORT_BY_RANK = "byRank";
    private String SORT_BY_SUIT = "bySuit";
    private String SORT_BY_BOTH = "both";

    private int ONE_NUMBER_OF_HANDS = 1;
    private int TWO_NUMBER_OF_HANDS = 2;
    private int THREE_NUMBER_OF_HANDS = 3;
    private int FIVE_NUMBER_OF_HANDS = 5;
    private int TEN_NUMBER_OF_HANDS = 10;
    private int HUNDRED_NUMBER_OF_HANDS = 100;

    private int NUMBER_OF_CARDS_PER_HAND = 7;

    private int OFFICIAL_SIZE_STANDARD = 52;
    private int OFFICIAL_SIZE_PINOCHLE = 48;
    private int OFFICIAL_SIZE_EUCHRE = 24;

    private HashMap<String, Integer> rankNameMap = new HashMap<>();
    private HashMap<String, Integer> suitNameMap = new HashMap<>();
    private HashMap<Character, Integer> suitSymbolMap = new HashMap<>();

    /**
     * A hand properly accepts a card.
     */
    @Test
    public void testHandAcceptsCard() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(STANDARD);
        game.setNumberOfHands(TWO_NUMBER_OF_HANDS);

        // deal method handles the accept card for a hand. Distributes 5 cards evenly among the number of hands
        game.deal();

        NewHand[] hands = game.getHands();
        assertTrue(hands.length == TWO_NUMBER_OF_HANDS);

        for(NewHand hand : hands) {
            assertTrue(hand.showCards().size() == NUMBER_OF_CARDS_PER_HAND);
        }
    }

    /**
     * A hand properly accepts a card.
     */
    @Test
    public void testHandAcceptsCardAfterShuffling() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(STANDARD);
        game.setNumberOfHands(TWO_NUMBER_OF_HANDS);
        game.getDeck().shuffle();

        List<Card> cardListAfterShuffling = new ArrayList<>();
        List<Card> cardList = game.getDeck().getCardList();

        for(Card card : cardList) {
            cardListAfterShuffling.add(card);
        }

        game.deal();

        NewHand[] hands = game.getHands();

        int k = 0;
        for (int i = 0; i < NUMBER_OF_CARDS_PER_HAND; i++) {
            for (int j = 0; j < TWO_NUMBER_OF_HANDS; j++) {
                assertTrue(game.getDeck().checkIfEqual(hands[j].showCards().get(i), cardListAfterShuffling.get(k)));
                k++;
            }
        }
    }

    /**
     * When a card is pulled from the hand, the hand now has one less card and that card is no longer in the deck.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testPullCardFromHandException() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(PINOCHLE);
        game.setNumberOfHands(ONE_NUMBER_OF_HANDS);
        game.deal();

        NewHand[] hands = game.getHands();

        //throws exception when we pull the card from an empty card list
        for(NewHand hand : hands) {
            for(int i=0; i<NUMBER_OF_CARDS_PER_HAND+1; i++) {
                hand.pullCard();
            }
        }
    }

    /**
     * When a card is pulled from the hand in a Euchre deck, the hand now has one less card and that card is no longer in the deck.
     */
    @Test
    public void testPullCardFromHandInEuchre() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(EUCHRE);
        game.setNumberOfHands(THREE_NUMBER_OF_HANDS);
        game.deal();

        NewHand[] hands = game.getHands();

        for(NewHand hand : hands) {
            int sizeBeforeCardRemoval = hand.showCards().size();

            Card pulledCard = hand.pullCard();

            assertFalse(hand.hasCard(pulledCard));
            assertFalse(game.getDeck().hasCard(pulledCard));
            assertTrue(hand.showCards().size() == sizeBeforeCardRemoval-1);
        }
    }

    /**
     * When a card is pulled from the hand in a Pinochle deck, the hand now has one less card and that card is no longer in the deck.
     */
    @Test
    public void testPullCardFromHandInPinochle() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(PINOCHLE);
        game.setNumberOfHands(THREE_NUMBER_OF_HANDS);
        game.deal();

        NewHand[] hands = game.getHands();

        for(NewHand hand : hands) {
            int sizeBeforeCardRemoval = hand.showCards().size();

            Card pulledCard = hand.pullCard();

            assertFalse(hand.hasCard(pulledCard));
            assertTrue(game.getDeck().hasCard(pulledCard)); // because in a pinochle deck there are two sets of euchre cards
            assertTrue(hand.showCards().size() == sizeBeforeCardRemoval-1);
        }
    }

    /**
     * When a card is pulled from the hand in a Vegas deck, the hand now has one less card and that card is no longer in the deck.
     */
    @Test
    public void testPullCardFromHandInVegas() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(VEGAS, 8);
        game.setNumberOfHands(TWO_NUMBER_OF_HANDS);
        game.deal();

        NewHand[] hands = game.getHands();

        for(NewHand hand : hands) {
            int sizeBeforeCardRemoval = hand.showCards().size();

            Card pulledCard = hand.pullCard();

            assertFalse(hand.hasCard(pulledCard));
            assertTrue(game.getDeck().hasCard(pulledCard)); // because in a vegas deck we pass the numberOfDecks parameter, which creates numberOfDecks*standardDeck
            assertTrue(hand.showCards().size() == sizeBeforeCardRemoval-1);
        }
    }

    /**
     * The hand tells accurately whether a particular card is present.
     */
    @Test
    public void testHandForACardToBePresent() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(STANDARD);
        game.setNumberOfHands(ONE_NUMBER_OF_HANDS);
        game.deal();

        Rank rank = new RankClass(RANK_NAME_TWO, RANK_PIPS_TWO);
        Suit suit = new SuitClass(SUIT_NAME_CLUBS, SUIT_SYMBOL_CLUBS);
        Card card = new CardClass(rank, suit);

        Rank rank1 = new RankClass(RANK_NAME_KING, RANK_PIPS_KING);
        Suit suit1 = new SuitClass(SUIT_NAME_CLUBS, SUIT_SYMBOL_CLUBS);
        Card card1 = new CardClass(rank1, suit1);

        Rank rank2 = new RankClass(RANK_NAME_TWO, RANK_PIPS_TWO);
        Suit suit2 = new SuitClass(SUIT_NAME_DIAMONDS, SUIT_SYMBOL_DIAMONDS);
        Card card2 = new CardClass(rank2, suit2);

        NewHand hand = game.getHands()[0];

        assertTrue(hand.hasCard(card));
        assertFalse(hand.hasCard(card1));
        assertFalse(hand.hasCard(card2));
    }

    /**
     * The hand tells accurately whether a particular rank is present.
     */
    @Test
    public void testHandForARankToBePresent() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(STANDARD);
        game.setNumberOfHands(ONE_NUMBER_OF_HANDS);
        game.deal();

        Rank rank = new RankClass(RANK_NAME_TWO, RANK_PIPS_TWO);
        Rank rank1 = new RankClass(RANK_NAME_KING, RANK_PIPS_KING);
        Rank rank2 = new RankClass(RANK_NAME_ACE, RANK_PIPS_ACE);
        Rank rank3 = new RankClass(RANK_NAME_TWO, RANK_PIPS_ACE);

        NewHand hand = game.getHands()[0];

        assertTrue(hand.hasRank(rank));
        assertFalse(hand.hasRank(rank1));
        assertFalse(hand.hasRank(rank2));
        assertFalse(hand.hasRank(rank3));
    }

    /**
     * The hand tells accurately whether a particular suit is present.
     */
    @Test
    public void testHandForASuitToBePresent() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(STANDARD);
        game.setNumberOfHands(ONE_NUMBER_OF_HANDS);
        game.deal();

        Suit suit = new SuitClass(SUIT_NAME_CLUBS, SUIT_SYMBOL_CLUBS);
        Suit suit1 = new SuitClass(SUIT_NAME_SPADES, SUIT_SYMBOL_SPADES);
        Suit suit2 = new SuitClass(SUIT_NAME_CLUBS, SUIT_SYMBOL_DIAMONDS);
        Suit suit3 = new SuitClass(SUIT_NAME_DIAMONDS, SUIT_SYMBOL_CLUBS);

        NewHand hand = game.getHands()[0];

        assertTrue(hand.hasSuit(suit));
        assertFalse(hand.hasSuit(suit1));
        assertFalse(hand.hasSuit(suit2));
        assertFalse(hand.hasSuit(suit3));
    }

    /**
     * Method to add suit values to it's map according to it's preference
     */
    private void addSuitValuesToTheMap() {
        suitNameMap.put(SUIT_NAME_CLUBS, 1);
        suitNameMap.put(SUIT_NAME_DIAMONDS, 2);
        suitNameMap.put(SUIT_NAME_HEARTS, 3);
        suitNameMap.put(SUIT_NAME_SPADES, 4);

        suitSymbolMap.put(SUIT_SYMBOL_CLUBS, 1);
        suitSymbolMap.put(SUIT_SYMBOL_DIAMONDS, 2);
        suitSymbolMap.put(SUIT_SYMBOL_HEARTS, 3);
        suitSymbolMap.put(SUIT_SYMBOL_SPADES, 4);
    }

    /**
     * The hand properly sorts the card by suit
     */
    @Test
    public void testSortBySuitForHand() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(STANDARD);
        game.setNumberOfHands(THREE_NUMBER_OF_HANDS);
        game.getDeck().shuffle();
        game.deal();

        addSuitValuesToTheMap();

        NewHand[] hands = game.getHands();

        for (NewHand hand : hands) {
            hand.shuffle();
            hand.sort(SORT_BY_SUIT);
        }

        for (NewHand hand : hands) {
            for (int i = 0; i < hand.showCards().size() - 1; i++) {
                Boolean checkSuitName = suitNameMap.get(hand.showCards().get(i).getSuit().getName()) <= suitNameMap.get(hand.showCards().get(i + 1).getSuit().getName());
                Boolean checkSuitSymbol = suitSymbolMap.get(hand.showCards().get(i).getSuit().getSymbol()) <= suitSymbolMap.get(hand.showCards().get(i + 1).getSuit().getSymbol());
                assertTrue(checkSuitName && checkSuitSymbol);
            }
        }
    }

    /**
     * Method to add rank values to it's map according to it's preference
     */
    private void addRankValuesToTheMap() {
        rankNameMap.put(RANK_NAME_TWO, 2);
        rankNameMap.put(RANK_NAME_THREE, 3);
        rankNameMap.put(RANK_NAME_FOUR, 4);
        rankNameMap.put(RANK_NAME_FIVE, 5);
        rankNameMap.put(RANK_NAME_SIX, 6);
        rankNameMap.put(RANK_NAME_SEVEN, 7);
        rankNameMap.put(RANK_NAME_EIGHT, 8);
        rankNameMap.put(RANK_NAME_NINE, 9);
        rankNameMap.put(RANK_NAME_TEN, 10);
        rankNameMap.put(RANK_NAME_JACK, 11);
        rankNameMap.put(RANK_NAME_QUEEN, 12);
        rankNameMap.put(RANK_NAME_KING, 13);
        rankNameMap.put(RANK_NAME_ACE, 14);
    }

    /**
     * The hand properly sorts the card by rank
     */
    @Test
    public void testSortByRankForHand() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(STANDARD);
        game.setNumberOfHands(THREE_NUMBER_OF_HANDS);
        game.getDeck().shuffle();
        game.deal();

        addRankValuesToTheMap();

        NewHand[] hands = game.getHands();

        for(NewHand hand : hands) {
            hand.shuffle();
            hand.sort(SORT_BY_RANK);
        }

        for(NewHand hand : hands) {
            for(int i=0; i<hand.showCards().size()-1; i++) {
                Boolean checkRankName = rankNameMap.get(hand.showCards().get(i).getRank().getName()) <= rankNameMap.get(hand.showCards().get(i+1).getRank().getName());
                assertTrue(checkRankName);
            }
        }
    }

    /**
     * The hand properly sorts the card by both
     */
    @Test
    public void testSortByBothForHand() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(STANDARD);
        game.setNumberOfHands(TWO_NUMBER_OF_HANDS);
        game.getDeck().shuffle();
        game.deal();

        addSuitValuesToTheMap();
        addRankValuesToTheMap();

        NewHand[] hands = game.getHands();

        for(NewHand hand : hands) {
            hand.shuffle();
            hand.sort(SORT_BY_BOTH);
        }

        for(NewHand hand : hands) {
            for(int i=0; i<hand.showCards().size()-1; i++) {
                Boolean checkSuitName = suitNameMap.get(hand.showCards().get(i).getSuit().getName()) <= suitNameMap.get(hand.showCards().get(i+1).getSuit().getName());
                Boolean checkSuitSymbol = suitSymbolMap.get(hand.showCards().get(i).getSuit().getSymbol()) <= suitSymbolMap.get(hand.showCards().get(i+1).getSuit().getSymbol());
                Boolean checkRankName = rankNameMap.get(hand.showCards().get(i).getRank().getName()) < rankNameMap.get(hand.showCards().get(i+1).getRank().getName());
                Boolean checkRankNameIfEqual = rankNameMap.get(hand.showCards().get(i).getRank().getName()) == rankNameMap.get(hand.showCards().get(i+1).getRank().getName());

                if(checkRankNameIfEqual) {
                    assertTrue(checkSuitName && checkSuitSymbol);
                } else {
                    assertTrue(checkRankName);
                }
            }
        }
    }

    /**
     * Method to check if two card lists are same
     * @param cardList1 : card list to compare
     * @param cardList2 : card list to compare
     * @return return true if two card lists are not same
     */
    private Boolean compareTwoCardList(List<Card> cardList1, List<Card> cardList2){
        Deck deck = new DeckClass();

        if(cardList1.size() != cardList2.size()) {
            return false;
        }

        for(int i=0; i<cardList1.size(); i++) {
            if(!deck.checkIfEqual(cardList1.get(i), cardList2.get(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * The hand properly shuffles the cards in it.
     */
    @Test
    public void testHandShuffling() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(PINOCHLE);
        game.setNumberOfHands(THREE_NUMBER_OF_HANDS);
        game.getDeck().shuffle();
        game.deal();

        NewHand[] hands = game.getHands();

        for(NewHand hand : hands) {
            List<Card> cardListBeforeShuffling = new ArrayList<>();
            List<Card> cardList = hand.showCards();

            for(Card card : cardList) {
                cardListBeforeShuffling.add(card);
            }

            // Shuffling the cards in hands
            hand.shuffle();
            List<Card> cardListAfterShuffling = hand.showCards();

            // Test to check whether the list of cards is shuffled
            assertTrue(compareTwoCardList(cardListBeforeShuffling, cardListAfterShuffling));
            assertFalse(compareTwoCardList(cardListBeforeShuffling, cardListBeforeShuffling));
        }
    }

    /**
     * Check hand instance of the game interface
     */
    @Test
    public void testGetHands() {
        GameFactory factory = new GameFactory();
        Game game = new GameClass(factory);
        game.createDeck(PINOCHLE);
        game.setNumberOfHands(THREE_NUMBER_OF_HANDS);
        game.getDeck().shuffle();
        game.deal();

        assertTrue(game.getHands() instanceof Hand[]);
        assertTrue(game.getHands() != null);

        game.createDeck(VEGAS);
        game.setNumberOfHands(THREE_NUMBER_OF_HANDS);
        game.getDeck().shuffle();
        game.deal();

        assertTrue(game.getHands() instanceof Hand[]);
        assertTrue(game.getHands() != null);
    }

    /**
     * Check whether hand has the card
     */
    @Test
    public void testHasHandCard() {
        Rank rank = new RankClass(RANK_NAME_TWO, RANK_PIPS_TWO);
        Suit suit = new SuitClass(SUIT_NAME_DIAMONDS, SUIT_SYMBOL_DIAMONDS);
        Card card = new CardClass(rank, suit);

        Rank rank1 = new RankClass(RANK_NAME_THREE, RANK_PIPS_THREE);
        Suit suit1 = new SuitClass(SUIT_NAME_DIAMONDS, SUIT_SYMBOL_DIAMONDS);
        Card card1 = new CardClass(rank1, suit1);

        Hand hand = new HandClass();
        hand.accept(card);
        assertTrue(hand.hasCard(card));
        assertFalse(hand.hasCard(card1));
    }

    /**
     * Number of card occurrences in hand
     */
    @Test
    public void testNumberOfCardOccurrences() {
        Rank rank = new RankClass(RANK_NAME_TWO, RANK_PIPS_TWO);
        Suit suit = new SuitClass(SUIT_NAME_DIAMONDS, SUIT_SYMBOL_DIAMONDS);
        Card card = new CardClass(rank, suit);

        Rank rank1 = new RankClass(RANK_NAME_THREE, RANK_PIPS_THREE);
        Suit suit1 = new SuitClass(SUIT_NAME_DIAMONDS, SUIT_SYMBOL_DIAMONDS);
        Card card1 = new CardClass(rank1, suit1);

        NewHand hand = new NewHandClass();
        hand.accept(card);
        hand.accept(card);
        hand.accept(card);
        hand.accept(card1);
        hand.accept(card1);

        assertNotEquals(5, hand.occurrencesInHand(card));
        assertNotEquals(0, hand.occurrencesInHand(card));
        assertNotEquals(2, hand.occurrencesInHand(card));
        assertEquals(3, hand.occurrencesInHand(card));
    }

    /**
     * Number of card occurrences in hand
     */
    @Test
    public void testNumberOfRankOccurrences() {
        Rank rank = new RankClass(RANK_NAME_TWO, RANK_PIPS_TWO);
        Suit suit = new SuitClass(SUIT_NAME_DIAMONDS, SUIT_SYMBOL_DIAMONDS);
        Card card = new CardClass(rank, suit);

        Rank rank1 = new RankClass(RANK_NAME_TWO, RANK_PIPS_TWO);
        Suit suit1 = new SuitClass(SUIT_NAME_HEARTS, SUIT_SYMBOL_HEARTS);
        Card card1 = new CardClass(rank1, suit1);

        NewHand hand = new NewHandClass();
        hand.accept(card);
        hand.accept(card);
        hand.accept(card);
        hand.accept(card1);
        hand.accept(card1);

        assertNotEquals(3, hand.occurrencesInHand(rank));
        assertNotEquals(0, hand.occurrencesInHand(rank));
        assertNotEquals(2, hand.occurrencesInHand(rank));
        assertEquals(5, hand.occurrencesInHand(rank));
    }

    /**
     * Number of card occurrences in hand
     */
    @Test
    public void testNumberOfRankOccurrencesWithPipsDifferent() {
        Rank rank = new RankClass(RANK_NAME_TWO, RANK_PIPS_TWO);
        Suit suit = new SuitClass(SUIT_NAME_DIAMONDS, SUIT_SYMBOL_DIAMONDS);
        Card card = new CardClass(rank, suit);

        Rank rank1 = new RankClass(RANK_NAME_TWO, RANK_PIPS_THREE);
        Suit suit1 = new SuitClass(SUIT_NAME_HEARTS, SUIT_SYMBOL_HEARTS);
        Card card1 = new CardClass(rank1, suit1);

        NewHand hand = new NewHandClass();
        hand.accept(card);
        hand.accept(card);
        hand.accept(card);
        hand.accept(card1);
        hand.accept(card1);

        assertNotEquals(5, hand.occurrencesInHand(rank));
        assertNotEquals(0, hand.occurrencesInHand(rank));
        assertNotEquals(2, hand.occurrencesInHand(rank));
        assertEquals(3, hand.occurrencesInHand(rank));
    }
}
