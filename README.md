/****************
TRADING CARD GAME
****************/

## Info about running the application

1) To execute the application, the maven configurations on the image mvnConfig.jpg can be used.(commandLine value on the image: clean install exec:java -Dexec.mainClass=App)

2) Step 1 performs maven clean install then executes main method in class App.

3) When the application run is completed it starts the game.



## Info about playing the game

1) The game greets the players("Welcome to the trading card game!").

2) The players are named as R2-D2 and C-3PO.

3) When the player's turn stars, the console shows a message with HP info. For example: Player R2-D2's turn, HP: 30
    * R2 always starts as first.

4) The console also shows the current status of the player. For example: Player R2-D2: available cards 3,4,5, number of cards in the deck 17, Mana: 1/1.
    * This operation repeats with each action.

5) To play a card, the player must type index of the card as console input.
    * Important Note: As an index, the list index in java should be used.
    * For example: Assume current status is "Player R2-D2: available cards 3,4,5, number of cards in the deck 17, Mana: 1/1."
        If the player want to play first card(mana 3),  '0' should be typed. For second card(with mana 4) '1' should be typed etc.
    * To end the turn 'e' or 'E' can be typed. At the end of the turn, an info message is shown. For example: "Player R2-D2's turn is over"
    * If the console input from the user is not a number(except 'e' or 'E'), the game warns the user. Similarly, if the user types an invalid index(for example:50) the game also warns the user.
    * There are also info messages for the following cases: Not enough mana to play a selected card, Bleed Out case and Overload case.

6) When a players HP is dropped to or below 0(either due to other player's cards or bleed out case), the other player wins the game. The example info message of this is: "######## Player R2-D2 is the winner, game is over! #######"
