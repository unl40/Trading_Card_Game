package com.example.test.controller.impl;

import com.example.test.controller.GameController;
import com.example.test.model.PlayerModel;
import com.example.test.service.GameLogicService;
import com.example.test.service.impl.GameLogicServiceImpl;

import java.util.Scanner;

public class GameControllerImpl implements GameController {

    public static final String END_TURN_INPUT = "e";
    private GameLogicService gameLogicService = new GameLogicServiceImpl();
    private PlayerModel playerOne = new PlayerModel();
    private PlayerModel playerTwo = new PlayerModel();

    public GameControllerImpl() {
        init();
    }

    private void init() {
        playerOne.setName("R2-D2");
        playerTwo.setName("C-3PO");
        playerOne.setYourTurn(true);
        playerOne.setOpponent(playerTwo);
        playerTwo.setOpponent(playerOne);
        for (int i = 0; i < 3; i++) {
            gameLogicService.drawCardFromDeck(playerOne);
            gameLogicService.drawCardFromDeck(playerTwo);
        }
    }

    @Override
    public void run() {
        while (!playerOne.isWinner() && !playerTwo.isWinner()) {
            PlayerModel playerOfTurn = playerOne.isYourTurn() ? playerOne : playerTwo;
            System.out.println(String.format("Player %s's turn, HP: %d", playerOfTurn.getName(), playerOfTurn.getHealth()));
            System.out.println(playerOfTurn.getPlayerInfo());
            receiveInputsFromPlayers(playerOfTurn);
        }
    }

    private void receiveInputsFromPlayers(PlayerModel playerOfTurn) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(playerOfTurn.getPlayerInfo());
            String userInput = scanner.nextLine();
            if (END_TURN_INPUT.equals(userInput.toLowerCase())) {
                gameLogicService.endTurn(playerOfTurn);
                break;
            } else {
                playCardFromHand(playerOfTurn, userInput);
            }
        }
    }

    private void playCardFromHand(PlayerModel playerOfTurn, String input) {
        try {
            int index = Integer.valueOf(input);
            gameLogicService.playCardFromHand(playerOfTurn, index);
            System.out.println(playerOfTurn.getPlayerInfo());
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Only a valid card index or end turn key \"e\" is accepted. Please try again!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: Invalid card index! Please try again!");
        }
    }


}
