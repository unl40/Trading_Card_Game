package com.example.test.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class PlayerModelTest {

    @InjectMocks
    PlayerModel player;

    @Mock
    CardModel card;

    @Test
    public void getPlayerInfoTest() {
        player.setName("BB-8");
        player.setCardsInHand(Arrays.asList(card));
        player.setCardsInDeck(Arrays.asList(card));
        player.setRemainingMana(5);
        player.setMaxMana(10);

        Mockito.when(card.getManaCost()).thenReturn(3);
        String playerInfo = player.getPlayerInfo();

        Assert.assertEquals("Player BB-8: available cards 3, number of cards in the deck 1, Mana: 5/10", playerInfo);
    }
}
