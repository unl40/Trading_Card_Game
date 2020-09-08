package com.example.test.model;

import com.example.test.strategy.impl.SimpleDamageStrategy;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(exclude = "opponent")
@ToString(exclude = "opponent")
public class PlayerModel {
    public static final int INITIAL_HEALTH = 30;
    public static final int INITIAL_MANA = 1;
    public static final int MAX_MANA_LIMIT = 10;
    public static final int MAX_CARD_IN_HAND = 5;
    public static final List<Integer> INITIAL_CARD_COSTS = Arrays.asList(0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8);
    public static final String PLAYER_INFO_TEMPLATE = "Player %s: available cards %s, number of cards in the deck %d, Mana: %d/%d";

    private int health;
    private int maxMana;
    private int remainingMana;
    private String name;
    private List<CardModel> cardsInHand = new LinkedList<>();
    private List<CardModel> cardsInDeck = new LinkedList<>();
    private PlayerModel opponent;
    private boolean yourTurn;
    private boolean winner;

    public PlayerModel() {
        init();
    }

    private void init() {
        health = INITIAL_HEALTH;
        maxMana = INITIAL_MANA;
        remainingMana = INITIAL_MANA;
        for (Integer cost : INITIAL_CARD_COSTS) {
            CardModel cardModel = new CardModel();
            cardModel.setManaCost(cost);
            cardModel.setDamageStrategy(SimpleDamageStrategy.from(cardModel));
            cardsInDeck.add(cardModel);
        }
    }

    public String getPlayerInfo() {
        return String.format(PLAYER_INFO_TEMPLATE,
                name,
                cardsInHand.stream().map(s -> String.valueOf(s.getManaCost())).collect(Collectors.joining(",")),
                cardsInDeck.size(),
                remainingMana,
                maxMana);
    }

}
