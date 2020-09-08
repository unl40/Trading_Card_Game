package com.example.test.service;

import com.example.test.model.PlayerModel;

public interface GameLogicService {
    void drawCardFromDeck(PlayerModel player);

    void playCardFromHand(PlayerModel player, int cardIndex);

    void endTurn(PlayerModel player);
}
