package com.example.test.service;

import com.example.test.model.CardModel;
import com.example.test.model.PlayerModel;
import com.example.test.service.impl.GameLogicServiceImpl;
import com.example.test.strategy.DamageStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameLogicServiceImplTest {

    @InjectMocks
    GameLogicServiceImpl gameLogicService = new GameLogicServiceImpl();

    @Mock
    DamageStrategy damageStrategy;

    CardModel oneManaCard;
    CardModel tenManaCard;
    PlayerModel fullHealthOpponent;
    PlayerModel oneHealthOpponent;

    @Before
    public void init() {
        oneManaCard = new CardModel();
        oneManaCard.setManaCost(1);
        oneManaCard.setDamageStrategy(damageStrategy);

        tenManaCard = new CardModel();
        tenManaCard.setManaCost(10);
        tenManaCard.setDamageStrategy(damageStrategy);

        fullHealthOpponent = new PlayerModel();
        fullHealthOpponent.setHealth(PlayerModel.INITIAL_HEALTH);

        oneHealthOpponent = new PlayerModel();
        oneHealthOpponent.setHealth(1);
    }

    @Test
    public void testDrawCardFromDeck_NormalDraw() {
        PlayerModel player = new PlayerModel();
        Assert.assertEquals(0, player.getCardsInHand().size());

        gameLogicService.drawCardFromDeck(player);

        Assert.assertEquals(1, player.getCardsInHand().size());
        Assert.assertEquals(PlayerModel.INITIAL_CARD_COSTS.size() - 1, player.getCardsInDeck().size());
    }

    @Test
    public void testDrawCardFromDeck_Overload() {
        PlayerModel player = new PlayerModel();
        Assert.assertEquals(0, player.getCardsInHand().size());

        int numOfDrawnCards = 6;
        for (int i = 0; i < numOfDrawnCards; i++) {
            gameLogicService.drawCardFromDeck(player);
        }

        Assert.assertEquals(PlayerModel.MAX_CARD_IN_HAND, player.getCardsInHand().size());
        Assert.assertEquals(PlayerModel.INITIAL_CARD_COSTS.size() - numOfDrawnCards, player.getCardsInDeck().size());
    }

    @Test
    public void testDrawCardFromDeck_BleedOut() {
        PlayerModel player = new PlayerModel();
        player.getCardsInDeck().clear();

        gameLogicService.drawCardFromDeck(player);

        Assert.assertEquals(PlayerModel.INITIAL_HEALTH - 1, player.getHealth());
    }


    @Test
    public void testDrawCardFromDeck_BleedOutLose() {
        PlayerModel player = new PlayerModel();
        player.getCardsInDeck().clear();
        player.setHealth(1);
        player.setOpponent(fullHealthOpponent);

        gameLogicService.drawCardFromDeck(player);

        Assert.assertTrue(player.getHealth() <= 0);
        Assert.assertTrue(player.getOpponent().isWinner());
    }

    @Test
    public void testPlayCardFromHand_NormalPlay() {
        PlayerModel player = new PlayerModel();
        player.setOpponent(fullHealthOpponent);
        player.getCardsInHand().add(oneManaCard);
        int cardNumInHand = player.getCardsInHand().size();
        Mockito.when(damageStrategy.getDamage()).thenReturn(oneManaCard.getManaCost());

        gameLogicService.playCardFromHand(player, 0);


        Assert.assertEquals(cardNumInHand - 1, player.getCardsInHand().size());
        Assert.assertEquals(oneManaCard.getDamage(), PlayerModel.INITIAL_HEALTH - fullHealthOpponent.getHealth());
    }

    @Test
    public void testPlayCardFromHand_NotEnoughMana() {
        PlayerModel player = new PlayerModel();
        player.getCardsInHand().add(tenManaCard);
        int cardNumInHand = player.getCardsInHand().size();

        gameLogicService.playCardFromHand(player, 0);

        Assert.assertEquals(cardNumInHand, player.getCardsInHand().size());
    }

    @Test
    public void testPlayCardFromHand_WinCase() {
        PlayerModel player = new PlayerModel();
        player.setRemainingMana(PlayerModel.MAX_MANA_LIMIT);
        player.getCardsInHand().add(tenManaCard);
        int numOfCardsInHand = player.getCardsInHand().size();
        player.setOpponent(oneHealthOpponent);
        Mockito.when(damageStrategy.getDamage()).thenReturn(tenManaCard.getManaCost());

        gameLogicService.playCardFromHand(player, 0);

        Assert.assertEquals(numOfCardsInHand - 1, player.getCardsInHand().size());
        Assert.assertTrue(player.isWinner());
    }

    @Test
    public void testEndTurn_NormalCase() {
        PlayerModel opponent = new PlayerModel();
        PlayerModel player = new PlayerModel();
        int maxMana = player.getMaxMana();
        int numOfCardsInHand = player.getCardsInHand().size();
        player.setOpponent(opponent);

        gameLogicService.endTurn(player);

        Assert.assertEquals(maxMana + 1, player.getMaxMana());
        Assert.assertEquals(player.getRemainingMana(), player.getMaxMana());
        Assert.assertFalse(player.isYourTurn());
        Assert.assertTrue(player.getOpponent().isYourTurn());
        Assert.assertEquals(numOfCardsInHand + 1, player.getCardsInHand().size());
    }


}
