# Implementation of GoFish using Iterator Pattern and Factory Pattern
This repository is a terminal application of [Go Fish Card Game](https://en.wikipedia.org/wiki/Go_Fish) using the iterator design pattern and factory design pattern. 

According to [BiCycle](https://www.bicyclecards.com/how-to-play/go-fish/),

The goal is to win the most "books" of cards. A book is a four of a kind. . . (meaning four of the same rank). The cards rank 
from ace (high) to two (low). The suits are not important, only the card numbers are relevant, such as two 3s, two 10s, and so 
on. 

Any player deals one card face up to each player. The player with the lowest card is the dealer. The dealer shuffles the 
cards, and the player on his right cuts them. The dealer completes the cut and deals the cards clockwise one at a time, face 
down, beginning with the player to his left. If two or three people are playing, each player receives seven cards. If four or 
five people are playing, each receives five cards. The remainder of the pack is placed face down on the table to form the 
stock. 

The player to the left of the dealer looks directly at any opponent and says, for example, "Give me your kings," usually 
addressing the opponent by name and specifying the rank he wants, from ace down to two. The player who is "fishing" must have 
at least one card of the rank he asked for in his hand. The player who is addressed must hand over all the cards requested. If 
he has none, he says, "Go fish!" and the player who made the request draws the top card of the stock and places it in his 
hand. If a player gets one or more cards of the named rank he asked for, he is entitled to ask the same or another player for 
a card. He can ask for the same card or a different one. So longas he succeeds in getting cards (makes a catch), his turn 
continues. When a player makes a catch, he must reveal the card so that the catch is verified. If a player gets the fourth 
card of a book, he shows all four cards, places them on the table face up in front of him, and plays again. 

If the player goes fishing without "making a catch" (does not receive a card he asked for), the turn passes to his left. The 
game ends when all thirteen books have been won. The winner is the player with the most books. During the game, if a player is 
left without cards, he may (when it's his turn to play), draw from the stock and then ask for cards of that rank. If there are 
no cards left in the stock, he is out of the game.

***For convenience, the game has been implemented to play only between two players.***

The implementation does not have any user interface and the main objective is to accomplish the game of GoFish to use Iterator 
Design Pattern and Factory Design Pattern. The [Iterator Pattern](https://github.com/srijitravi94/GoFish-Iterator-Pattern/blob/master/src/main/java/edu/northeastern/ccs/cs5500/classes/NewHandClass.java#L102) is implemented from the scratch 
so that the players can repeatedly see and remove the cards from hand. The [Iterator.java](https://github.com/srijitravi94/GoFish-Iterator-Pattern/blob/master/src/main/java/edu/northeastern/ccs/cs5500/interfaces/Iterator.java) interface is declared to mimic the functionality of a default iterator. All the logic and implementation of GoFish game is implemented in [GameClass.java](https://github.com/srijitravi94/GoFish-Iterator-Pattern/blob/master/src/main/java/edu/northeastern/ccs/cs5500/classes/GameClass.java). The factory pattern is implemented in [GameFactory.java.](https://github.com/srijitravi94/GoFish-Iterator-Pattern/blob/master/src/main/java/edu/northeastern/ccs/cs5500/classes/GameFactory.java) The factory pattern was used to create specialized versions of the game by creating subclasses of GameClass.  

Various functions that implement the iterator pattern are : 
  * **[hasCard(Card card)](https://github.com/srijitravi94/GoFish-Iterator-Pattern/blob/master/src/main/java/edu/northeastern/ccs/cs5500/classes/NewHandClass.java#L47)**  :  Takes a Card parameter and iterates over the player's hand to check whether the card is present in the hand.
  
  * **[occurencesInHand(Card card)](https://github.com/srijitravi94/GoFish-Iterator-Pattern/blob/master/src/main/java/edu/northeastern/ccs/cs5500/classes/NewHandClass.java#L63)**  : Takes a Card parameter and iterates over the player's hand to count the number of occurences of the given card.
  
  * **[occurencesInHand(Rank rank)](https://github.com/srijitravi94/GoFish-Iterator-Pattern/blob/master/src/main/java/edu/northeastern/ccs/cs5500/classes/NewHandClass.java#L80)**  : Takes a Rank parameter and iterates over the player's hand to count the number of occurences of the given rank in the card list.
  
  * **[removeCards(NewHand hand, Rank rank)](https://github.com/srijitravi94/GoFish-Iterator-Pattern/blob/master/src/main/java/edu/northeastern/ccs/cs5500/classes/GameClass.java#L274)**   : Takes Hand and Rank as parameter and iterate over the cards in the hand and remove the particular card from the hand.


After the game is completed, [winners](https://github.com/srijitravi94/GoFish-Iterator-Pattern/blob/master/src/main/java/edu/northeastern/ccs/cs5500/classes/GameClass.java#L320) are evaluated based on the number of points they won during the game play.
