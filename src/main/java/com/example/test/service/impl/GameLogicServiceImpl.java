package com.example.test.service.impl;

import com.example.test.model.CardModel;
import com.example.test.model.PlayerModel;
import com.example.test.service.GameLogicService;

import java.util.Random;

public class GameLogicServiceImpl implements GameLogicService {

    @Override
    public void drawCardFromDeck(PlayerModel player) {
        if (player.getCardsInDeck() != null && player.getCardsInDeck().size() != 0) {
            CardModel drawnCard = player.getCardsInDeck().get(new Random().nextInt(player.getCardsInDeck().size()));
            player.getCardsInDeck().remove(drawnCard);
            if (player.getCardsInHand() != null && player.getCardsInHand().size() < 5) {
                player.getCardsInHand().add(drawnCard);
            } else {
                System.out.println(String.format("Player %s: Your hand is too full!", player.getName()));
            }
        } else {
            player.setHealth(player.getHealth() - 1);
            System.out.println(String.format("Player %s: You are out of cards, Bleeding Out applies!", player.getName()));
            checkBleedOutWin(player);
        }
    }

    private void checkBleedOutWin(PlayerModel player) {
        if (player.getHealth() <= 0) {
            System.out.println(String.format("######## Player %s is the winner, game is over! #######", player.getOpponent().getPlayerInfo()));
            player.getOpponent().setWinner(true);
        }
    }

    @Override
    public void playCardFromHand(PlayerModel player, int cardIndex) {
        CardModel selectedCard = player.getCardsInHand().get(cardIndex);
        if (player.getRemainingMana() >= selectedCard.getManaCost()) {
            player.getCardsInHand().remove(selectedCard);
            player.setRemainingMana(player.getRemainingMana() - selectedCard.getManaCost());
            player.getOpponent().setHealth(player.getOpponent().getHealth() - selectedCard.getDamage());
            System.out.println(String.format("Player %s played a card with %d mana / Player %s HP: %d",
                    player.getName(), selectedCard.getManaCost(), player.getOpponent().getName(), player.getOpponent().getHealth()));
            checkCardPlayWin(player);
        } else {
            System.out.println(String.format("Player %s: Your don't have enough mana!", player.getName()));
        }
    }

    private void checkCardPlayWin(PlayerModel player) {
        if (player.getOpponent().getHealth() <= 0) {
            System.out.println(String.format("######## Player %s is the winner, game is over! #######", player.getName()));
            player.setWinner(true);
        }
    }

    @Override
    public void endTurn(PlayerModel player) {
        player.setMaxMana(Math.min(player.getMaxMana() + 1, PlayerModel.MAX_MANA_LIMIT));
        player.setRemainingMana(player.getMaxMana());
        player.setYourTurn(false);
        player.getOpponent().setYourTurn(true);
        drawCardFromDeck(player);
        System.out.println(String.format("Player %s's turn is over! HP: %d\n\n", player.getName(), player.getHealth()));
    }

}
