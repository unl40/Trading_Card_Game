package com.example.test.model;

import com.example.test.strategy.DamageStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CardModelTest {

    @InjectMocks
    CardModel cardModel;

    @Mock
    DamageStrategy damageStrategy;

    @Test
    public void testGetDamage_SimpleDamageStrategy() {
        cardModel.setManaCost(5);
        Mockito.when(damageStrategy.getDamage()).thenReturn(cardModel.getManaCost());

        int damage = cardModel.getDamage();

        Assert.assertEquals(5, damage);
    }
}
