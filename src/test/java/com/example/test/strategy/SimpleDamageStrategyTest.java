package com.example.test.strategy;

import com.example.test.model.CardModel;
import com.example.test.strategy.impl.SimpleDamageStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleDamageStrategyTest {

//    FIXME: Better way to test
//    @InjectMocks
//    SimpleDamageStrategy simpleDamageStrategy;
//
//    @Mock
//    CardModel card;
//
//    @Test
//    public void getDamageTest() {
//        simpleDamageStrategy.setCardModel(card);
//        Mockito.when(card.getDamageStrategy()).thenReturn(simpleDamageStrategy);
//        Mockito.when(card.getManaCost()).thenReturn(5);
//
//        int damage = card.getDamage();
//
//        Assert.assertEquals(5, damage);
//    }

    @Test
    public void getDamageTest() {
        CardModel card = new CardModel();
        card.setManaCost(5);
        DamageStrategy simpleDamageStrategy = SimpleDamageStrategy.from(card);
        card.setDamageStrategy(simpleDamageStrategy);

        int damage = card.getDamage();

        Assert.assertEquals(5, damage);
    }
}
